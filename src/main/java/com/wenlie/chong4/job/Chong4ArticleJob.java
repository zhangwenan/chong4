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
public class Chong4ArticleJob {


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


    private Logger logger = LoggerFactory.getLogger(Chong4ArticleJob.class); // 日志
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


    public void execute(){
        init();

        List<Article> nullArticleList = articleService.getNullArticles(50);
        String contentReg = "<div class=\"textbox-content\">\\s*(?<content>[\\s\\S]+?)" +
                "\\s*</div>" +
                "\\s*<div class=\"textbox-more\">";

        String refArticleHtmlReg = "<div class=\"aboutlk\"><strong>相关文章推荐</strong>" +
                "(<br /><a href=\"/read\\.php\\?\\d+\" target=\"_blank\">[\\s\\S]+?</a>)+" +
                "</div>" +
                "\\s*<div class=\"cydblink\">";

        String articleIdReg = "<a href=\"/read\\.php\\?(?<articleId>\\d+)\" target=\"_blank\">";

        Pattern contentPattern = Pattern.compile(contentReg);
        Pattern refArticlePattern = Pattern.compile(refArticleHtmlReg);
        Pattern articleIdPattern = Pattern.compile(articleIdReg);

        String refArticleHtml = "";
        for(int i=0; i<nullArticleList.size(); i++){

            Article article = nullArticleList.get(i);
            try {
                HttpGet httpGet = new HttpGet("http://www.chong4.com.cn/read.php?" + article.getId());
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                    logger.warn(DateUtil.formatDate(new Date(), timeStr) + "，获取文章（id：" + article.getId() +"）内容，发生异常！" +
                                "\r\n返回状态码" + response.getStatusLine().getStatusCode() +
                                "\r\n返回Html：" + EntityUtils.toString(entity));
                    initialized = Boolean.FALSE;
                    return;
                }

                String responseHtml = EntityUtils.toString(entity);
                Matcher contentMatcher = contentPattern.matcher(responseHtml);
                if(contentMatcher.find()){
                    article.setContent(contentMatcher.group("content"));
                    articleService.update(article);
                }

                Matcher refArticleHtmlMatcher = refArticlePattern.matcher(responseHtml);
                if(refArticleHtmlMatcher.find()){
                    refArticleHtml = refArticleHtmlMatcher.group(0);
                }

                Matcher articleIdMatcher = articleIdPattern.matcher(refArticleHtml);
                while (articleIdMatcher.find()){
                    RefArticle refArticle = new RefArticle(article.getId(),
                            Integer.parseInt(articleIdMatcher.group("articleId")));
                    refArticleService.add(refArticle);
                }
                logger.warn(DateUtil.formatDate(new Date(), timeStr) +
                        "，成功获取文章（id：" + article.getId() +"）内容！");

            }
            catch (IOException e) {
                e.printStackTrace();
            }


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
