package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class TopicArticle {

    private int id;

    // 主题id
    private int topic_id;

    // 文章id
    private int article_id;



    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `topic_id` int(11) NULL COMMENT '主题id',
            `article_id` int(11) NULL COMMENT '文章id',*/
}
