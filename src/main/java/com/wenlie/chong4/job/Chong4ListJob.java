package com.wenlie.chong4.job;

import cn.weili.util.DateUtil;
import com.wenlie.chong4.bean.*;
import com.wenlie.chong4.service.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    ArticleService articleService;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicArticleService topicArticleService;

    @Autowired
    TagService tagService;

    @Autowired
    RefArticleService refArticleService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    AnchorService anchorService;


    private Logger logger = LoggerFactory.getLogger(Chong4ListJob.class); // 日志
    private String timeStr = "MM-dd HH:mm:ss";

    private Boolean initialized = Boolean.FALSE;
    private CookieStore cookieStore;
    private CloseableHttpClient httpClient;
    private int page = 1;

    private int pageIndex = 0;

    /**
     * 初始化，
     * 设置 cookie 容器，
     * 设置 httpClient
     */
    public void init(){
        if(initialized) return;
        setCookieStore(new BasicCookieStore());
        setHttpClient(HttpClients.custom().setDefaultCookieStore(cookieStore).build());
        initialized = Boolean.TRUE;
    }


    public void dealTags(){
        init();

        logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，开始采集标签");


        List<Tag> tagList = getTags();
        tagService.batchAdd(tagList);

        String reg = "<tr>" +
                "\\s*<td><a href=\"/read\\.php\\?(?<articleId>\\d+)\" target=\"_blank\">[\\s\\S]+?</a></td>" +
                "\\s*<td>[\\s\\S]+?</td>" +
                "\\s*<td>\\d+/\\d+/\\d+</td>" +
                "\\s*</tr>";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = null;


        for (int i=0; i<tagList.size(); i++){

            int page = 1;
            while (page > 0){

                try {
                    int tagId = tagService.getByName(tagList.get(i).getName()).getId();
                    String tagNameEncoded = URLEncoder.encode(tagList.get(i).getName(), "UTF-8");


                    HttpGet httpGet = new HttpGet("http://www.chong4.com.cn/tag.php?tag=" + tagNameEncoded + "&mode=2&page=" + page);

                    logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，当前采集标签【" + tagList.get(i).getName()+
                            "】第" + page+ "页");

                    CloseableHttpResponse response = null;
                    response = httpClient.execute(httpGet);
                    HttpEntity entity = response.getEntity();

                    String responseHtml = EntityUtils.toString(entity);
                    matcher = pattern.matcher(responseHtml);
                    int count = 0;
                    while (matcher.find()){
                        count++;
                        articleTagService.add(new ArticleTag(tagId, Integer.parseInt(matcher.group("articleId"))));
                    }

                    logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，获得" + count+ "篇文章");

                    if(count<50){
                        page = -1;
                    }
                    else{
                        page ++;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void execute(){
        init();
        /*List<Integer> intArr = new ArrayList<Integer>();
        intArr.add(868);
        intArr.add(852);
        intArr.add(848);
        intArr.add(847);
        intArr.add(821);
        intArr.add(815);
        intArr.add(757);
        intArr.add(0);

        page = intArr.get(pageIndex);*/



        if(page == 0){
            logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，文章列表已经采集完毕");
            SettingContext.put("isCollecting", Boolean.FALSE);
            return;
        }






        HttpGet httpGet = new HttpGet("http://www.chong4.com.cn/index.php?mode=1&page=" + page);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity entity = response.getEntity();


        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

            try {
                logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，监控文章列表时，发生异常！" +
                        "\r\n返回状态码" + response.getStatusLine().getStatusCode() +
                        "\r\n返回Html：" + EntityUtils.toString(entity));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }

        String responseHtml = null;
        try {
            responseHtml = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Article> articleList = getArticleList(responseHtml);
        if(articleList.size() < 12){
            logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，采集文章列表第" + page + "页时，数量少于12个!" +
                    "\r\nHtml为：" + responseHtml
            );

        }
        articleService.batchAdd(articleList);

        String topicHtml = getTopicHtml(responseHtml);
        dealTopicHtml(topicHtml);

        logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，文章列表第" + page + "页采集完毕!");
        page = getNextPage(responseHtml);
        /*pageIndex++;*/
        execute();



        /*// 获取40个关键词
        List<Keyword> keywords = keywordService.getKeywordsHasNoItems(50);
        if(keywords == null || keywords.size() < 1) return;
        *//*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，取出（" + keywords.size() + "）个关键词...");*//*

        *//*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，开始从阿里妈妈获取商品...");*//*

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
                *//*logger.warn(DateUtil.formatDate(new Date(), timeStr) + "," +
                        tempKeyword.getName() + ":" + items.toString());*//*
            }
            else{
                logger.warn(DateUtil.formatDate(new Date(), timeStr) + "," +
                        tempKeyword.getName() + ": 阿里妈妈没有相关商品");
            }
            tempKeyword.setHasItems("yes");
            keywordService.update(tempKeyword);

        }
        logger.warn(DateUtil.formatDate(new Date(), timeStr) + "结束");*/
    }



    public List<Tag> getTags(){
        init();

        HttpGet httpGet = new HttpGet("http://www.chong4.com.cn/tag.php");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity entity = response.getEntity();

        String responseHtml = null;
        try {
            responseHtml = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reg = "<a href=\"tag\\.php\\?tag=[^\"]+?\" title=\"[^\"]+?\" target=\"_blank\">(?<tagName>[\\s\\S]+?)</a>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(responseHtml);

        List<Tag> tagList = new ArrayList<Tag>();
        while (matcher.find()){
            tagList.add(new Tag(matcher.group("tagName")));
        }
        return tagList;
    }


    public List<Article> getArticleList(String html){

        /**
         * 正则匹配注意：
         * 转义：  \\.  \\[   \\?  \\|
         */

        String regStr = "<div class=\"textbox\">" +
                "\\s*<div class=\"textbox-title\">" +
                "\\s*<span id=\"starid(?<articleId>\\d+)\"><img src=\"images/others/(?<starred>(un)?starred)\\.gif\" /></span>" +
                "<h2><a href=\"/read\\.php\\?\\d+\" target=\"_blank\">(?<title>[\\s\\S]+?)</a></h2>" +
                "\\s*<div class=\"textbox-label\">\\[ (?<publishTime>\\d+/\\d+/\\d+ \\d+:\\d+) \\| by (?<author>[\\s\\S]+?) ]</div>" +
                "\\s*</div>" +
                "\\s*<div class=\"textbox-content\">" +
                "\\s*(?<summary>[\\s\\S]+?)" +
                "(<div class=\"readmore\">" +
                "<img src=\"/image/readmore\\.gif\" /><a href=\"/read\\.php\\?\\d+\" title=\"点击阅读全文\" target=\"_blank\">阅读全文</a>" +
                "</div>)?" +
                "\\s*</div>" +
                "\\s*<div class=\"textbox-bottom\">" +
                "\\s*分类：<a href=\"/tag\\.php\\?tag=[^\"]+\" target=\"_blank\">[\\s\\S]+?</a>" +
                "(，<a href=\"/tag\\.php\\?tag=[^\"]+\" target=\"_blank\">[\\s\\S]+?</a>){0,1}" +
                " \\| 评论\\((?<commentCount>\\d+)\\) \\| 阅读\\((?<readCount>\\d+)\\)" +
                "\\s*</div>" +
                "\\s*</div>";


        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(html);
        /*int count = 0;*/

        List<Article> articleList = new ArrayList<Article>();
        while (matcher.find()){
            int articleId = Integer.parseInt(matcher.group("articleId"));
            String starred = matcher.group("starred");
            String title = matcher.group("title");
            Date publishTime = DateUtil.parseDate(matcher.group("publishTime"), "yyyy/MM/dd HH:mm");
            String author = matcher.group("author");
            String summary = matcher.group("summary");
            int commentCount = Integer.parseInt(matcher.group("commentCount"));
            int readCount = Integer.parseInt(matcher.group("readCount"));

            Article article = new Article();
            article.setId(articleId);
            article.setTitle(title);
            article.setPublishTime(publishTime);
            article.setAuthor(author);
            article.setSummary(summary);
            article.setCommentCount(commentCount);
            article.setReadCount(readCount);
            if(starred.equals("unstarred")){
                article.setStarred(StarredStatus.no);
            }
            else if(starred.equals("starred")){
                article.setStarred(StarredStatus.yes);
            }


            articleList.add(article);
        }

        return articleList.size()>0?articleList:null;
    }



    // 获取下一页的页码
    private int getNextPage(String html){

        String regStr = "page=(?<page>\\d+)\" class=\"pagesnext\">下一页</a>";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(html);
        if(matcher.find()){
            return Integer.parseInt(matcher.group("page"));
        }
        else{
            return 0;
        }
    }


    // 把推荐主题部分的html 匹配出来
    private String getTopicHtml(String html){
        String reg = "<div class=\"panel\">" +
                "\\s*<h3>主题推荐</h3>" +
                "\\s*<div class=\"panel-content\">" +
                "\\s*<ul>" +

                "(\\s*<li>.+</li>)+" +

                "\\s*</ul>" +
                "\\s*</div>" +
                "\\s*</div>";


        Pattern pattern = Pattern.compile(reg);
        java.util.regex.Matcher matcher = pattern.matcher(html);

        if (matcher.find()){
            return matcher.group(0);

        }
        else{
            return null;
        }

    }


    private void dealTopicHtml(String topicHtml){
        String regStr = "\\s*<li>(?<topicName>.+)</li>" +
                "(?<articles>(\\s*<li><a href=\"http://www\\.chong4\\.com\\.cn/read\\.php\\?\\d+\" title=\".+\" target=\"_blank\">.+</a></li>\\s*)+)";

        String articleStr = "\\s*<li><a href=\"http://www\\.chong4\\.com\\.cn/read\\.php\\?(?<articleId>\\d+)\" title=\".+\" target=\"_blank\">(?<articleTitle>.+)</a></li>\\s*";

        Pattern pattern = Pattern.compile(regStr);
        java.util.regex.Matcher matcher = pattern.matcher(topicHtml);

        Pattern pattern2 = Pattern.compile(articleStr);


        while (matcher.find()){

            String topicName = matcher.group("topicName");
            Topic topic = new Topic(topicName);
            topicService.add(topic);
            topic = topicService.getByName(topicName);


            String articlesHtml = matcher.group("articles");
            java.util.regex.Matcher matcher2 = pattern2.matcher(articlesHtml);

            List<TopicArticle> topicArticles = new ArrayList<TopicArticle>();

            while (matcher2.find()){
                topicArticles.add(new TopicArticle(topic.getId(), Integer.parseInt(matcher2.group("articleId"))));
            }
            topicArticleService.batchAdd(topicArticles);
        }

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
