package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class TopicArticle {

    public TopicArticle() {
    }

    public TopicArticle(int topic_id, int article_id) {
        this.topicId = topic_id;
        this.articleId = article_id;
    }

    private int id;

    // 主题id
    private int topicId;

    // 文章id
    private int articleId;



    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `topic_id` int(11) NULL COMMENT '主题id',
            `article_id` int(11) NULL COMMENT '文章id',*/
}
