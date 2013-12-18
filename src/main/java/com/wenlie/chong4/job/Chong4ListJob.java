package com.wenlie.chong4.job;

import cn.weili.util.DateUtil;
import com.wenlie.chong4.bean.AlimamaItem;
import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.service.AlimamaItemService;
import com.wenlie.chong4.service.KeywordItemIdService;
import com.wenlie.chong4.service.KeywordService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-12-3
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */
public class Chong4ListJob {

    @Autowired
    KeywordService keywordService;

    @Autowired
    KeywordItemIdService keywordItemIdService;

    @Autowired
    AlimamaItemService alimamaItemService;


    private Logger logger = LoggerFactory.getLogger(Chong4ListJob.class); // 日志
    private String timeStr = "MM-dd HH:mm:ss";

    private Boolean initialized = Boolean.FALSE;
    private CookieStore cookieStore;
    private CloseableHttpClient httpClient;

    /**
     * 初始化，阿里妈妈登录状态
     */
    public void init(){
        if(initialized) return;
        setCookieStore(new BasicCookieStore());
        setHttpClient(HttpClients.custom().setDefaultCookieStore(cookieStore).build());

        HttpGet httpGet = new HttpGet("http://www.alimama.com/index.htm");
        try {
            httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        List<Cookie> cookieList = cookieStore.getCookies();
        String _tb_token = "";
        for(int i=0; i<cookieList.size(); i++){
            if(cookieList.get(i).getName().equals("_tb_token_")){
                _tb_token = cookieList.get(i).getValue();
            }
        }


        HttpPost httpPost = new HttpPost("https://www.alimama.com/member/minilogin_act.htm");
        List <NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("_tb_token_", _tb_token));
        nvps.add(new BasicNameValuePair("style", ""));
        nvps.add(new BasicNameValuePair("redirect", ""));
        nvps.add(new BasicNameValuePair("proxy", "http://www.alimama.com/proxy.htm"));
        nvps.add(new BasicNameValuePair("logname", "ligongzhangwenan@sina.com"));
        nvps.add(new BasicNameValuePair("originalLogpasswd", "i79o4324"));
        nvps.add(new BasicNameValuePair("logpasswd", "a887af4040374792e1f8f6d395b0f638"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.setHeader("Referer", "http://www.alimama.com/member/minilogin.htm?&proxy=http://www.alimama.com/proxy.htm");
            httpPost.setHeader("Host", "www.alimama.com");

            // 登录
            httpClient.execute(httpPost);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        initialized = Boolean.TRUE;
    }

    public void execute(){
        logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，开始任务：");
        // 获取40个关键词
        List<Keyword> keywords = keywordService.getKeywordsHasNoItems(50);
        if(keywords == null || keywords.size() < 1) return;
        /*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，取出（" + keywords.size() + "）个关键词...");*/

        /*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，开始从阿里妈妈获取商品...");*/

        // 根据关键词，获取商品id
        for (int i=0; i<keywords.size(); i++){
            Keyword tempKeyword = keywords.get(i);

            // 根据关键词，从阿里妈妈获取相关商品
            List<AlimamaItem> items = getItemsByKeyword(tempKeyword.getName());

            // 如果根据该关键词获取到的
            // 商品数量大于0
            if(items != null && items.size()>0){
                keywordItemIdService.batchAdd(tempKeyword.getName(), items);
                alimamaItemService.batchAdd(items);
                /*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "," +
                        tempKeyword.getName() + ":" + items.toString());*/
            }
            else{
                logger.warn(DateUtil.formatDate(new Date(), timeStr) + "," +
                        tempKeyword.getName() + ": 阿里妈妈没有相关商品");
            }
            tempKeyword.setHasItems("yes");
            keywordService.update(tempKeyword);

        }
        logger.warn(DateUtil.formatDate(new Date(), timeStr) + "结束");
    }


    public List<AlimamaItem> getItemsByKeyword(String keyword){
        init();
        try {
            /*logger.warn(now.toString() + "开始->根据关键词，从阿里妈妈后台获取商品数据：");
            logger.warn("当前处理关键词: " + keyword);*/
            HttpGet httpGetItems = new HttpGet("http://u.alimama.com/union/spread/selfservice/merchandisePromotion.htm?" +
                    "cat=&discountId=&pidvid=&_fmu.a._0.t=1&_fmu.a._0.pe=40&_fmu.a._0.l=&" +
                    "_fmu.a._0.so=_totalnum&" +
                    "c=&rewrite=&cat=&mid=&" +
                    "searchType=0&" +
                    "q=" + URLEncoder.encode(keyword, "GBK") + "&" +
                    "_fmu.a._0.u=&_fmu.a._0.s=&_fmu.a._0.sta=&_fmu.a._0.end=&_fmu.a._0.st=&_fmu.a._0.en=&_fmu.a._0.star=0&loc=");
            CloseableHttpResponse httpResponse3 = httpClient.execute(httpGetItems);
            HttpEntity entity3 = httpResponse3.getEntity();

            if(httpResponse3.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，处理关键词【" + keyword + "】异常！" +
                        "\r\n阿里妈妈返回状态" + httpResponse3.getStatusLine().getStatusCode() +
                        "\r\n阿里妈妈返回Html：" + EntityUtils.toString(entity3));
                setInitialized(Boolean.FALSE);
                init();
                return null;
            }

            /*logger.warn("获取页面Html时，返回的状态：" + httpResponse3.getStatusLine());*/
            String responseHtml = EntityUtils.toString(entity3);


            // 需要登录
            if(needLogin(responseHtml)){
                setInitialized(Boolean.FALSE);
                logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，处理关键词【"+keyword+"】，需要重新登录...");
                return getItemsByKeyword(keyword);
            }


            List<AlimamaItem> items = getItemIdsFromHtml(responseHtml);
            if(items == null){
                /*logger.warn("根据【" + keyword + "】无法查到相关商品!");*/
                logger.warn("查询【" + keyword + "】时，" +
                        "\r\n返回的状态码：" +
                        httpResponse3.getStatusLine().getStatusCode()
                        +"\r\n返回的HTML：" + responseHtml);
            }
            else{
                /*logger.warn("根据【" + keyword + "】获取到 (" + items.size() +") 条商品信息: ");*/
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=0; i<items.size(); i++){
                    stringBuilder.append(items.get(i).getItemId());
                    stringBuilder.append(",");
                    stringBuilder.append(items.get(i).getItemTitle());
                    stringBuilder.append("\r\n");
                }
                /*logger.warn(stringBuilder.toString());*/
            }

            return items;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private Boolean needLogin(String html){
        String needLogin = "<iframe name=\"mmLoginIfr\" id=\"J_mmLoginIfr\"";
        Pattern needLoginPattern = Pattern.compile(needLogin);
        Matcher needLoginMatcher = needLoginPattern.matcher(html);
        if(needLoginMatcher.find()){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    private List<AlimamaItem> getItemIdsFromHtml(String html){
        String nothingReg = "建议您尝试其他关键字重新搜索";
        Pattern nothingPattern = Pattern.compile(nothingReg);
        Matcher nothingMatcher = nothingPattern.matcher(html);
        if(nothingMatcher.find()) return null;





        String reg = "<tr zhekou=\"(?<zhekou>[^\"]+)\" zhekoujia=\"(?<zhekoujia>[^\"]+)\">\\s*<td>\\s*" +
                "<input type=\"checkbox\" value=\"(?<itemId>\\d+)\" name=\"linkexport\"/>\\s*</td>" +
                "\\s*<td align=\"left\">\\s*<div class=\"list-info\">" +
                "(\\s*<span class=\"count-label red\">\\[\\S*]</span>)*" +
                "\\s*<a href=\"[^\"]*\" class=\"pic\" target=\"_blank\">" +
                "<img src=\"(?<itemPic>[^\"]*)\" onerror=\"[^\"]*\"></a>" +
                "\\s*<a href=\"[^\"]*\" target=\"_blank\">(?<itemTitle>.+)</a>" +
                "\\s*<p class=\"shopkeeper\">" +
                "\\s*掌柜：\\S*" +
                "\\s*<span class=\"J_WangWang\" data-encode=\"true\" data-nick=\"\\S*\" data-display=\"inline\"" +
                "\\s*data-item=\"\\d+\" data-icon=\"small\"></span>\\s*</p>" +
                "\\s*<p><a biz30day=\"\\d+\" commentCount=\"\\d+\" oid=\"\\d+\" href=\"[^\"]+\" class=\"blue-link shop-detail\" target=\"_blank\">店铺推广详情&gt;&gt;</a></p>" +
                "\\s*</div>\\s+</td>\\s+<td align=\"right\">" +
                "\\s*(?<discount>[0-9\\.]*|-)折?" +
                "\\s*</td>" +
                "\\s*<td align=\"right\">[0-9\\.]+元</td>" +
                "\\s*<td align=\"right\">[0-9\\.]+%</td>" +
                "\\s*<td align=\"right\"><span class=\"ok\">[0-9\\.]+元</span></td>" +
                "\\s*<td align=\"right\">[0-9\\.]+件</td>" +
                "\\s*<td align=\"right\">[0-9\\.]+元</td>" +
                "\\s*<td align=\"center\">" +
                "\\s*<p class=\"use-now\">" +
                "\\s*<a class=\"btn btn-blue get-code\" href=\"#\" target=\"_blank\" auctionid=\"\\d+\">立即推广</a>" +
                "\\s*</br>" +
                "(\\s*<a href=\"javascript:void\\(0\\);\" did=\"\\d+\" class=\"more-commission\">获取更高佣金∨</a>)*" +
                "\\s*</p>\\s*</td>\\s*</tr>";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(html);
        /*int count = 0;*/

        List<AlimamaItem> alimamaItemList = new ArrayList<AlimamaItem>();
        while (matcher.find()){
            /*count++;*/
            long itemId = Long.parseLong(matcher.group("itemId"));
            String itemTitle = matcher.group("itemTitle").replaceAll("<span>", "").replaceAll("</span>","");
            alimamaItemList.add(new AlimamaItem(itemId, itemTitle));
        }

        return alimamaItemList.size()>0?alimamaItemList:null;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
