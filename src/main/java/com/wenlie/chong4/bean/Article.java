package com.wenlie.chong4.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class Article {

    public Article(){
        if(this.starred == null){
            this.starred = StarredStatus.no;
        }
    }

    // 文章id
    private int id;

    // 标题
    private String title;


    // 摘要
    private String summary;

    // 内容
    private String content;

    // 作者
    private String author;

    // 采集的时间
    private Date createTime;

    // 发布时间
    private Date publishTime;

    // 是否加星推荐
    private StarredStatus starred;

    // 评论数
    private int commentCount;

    // 阅读数
    private int readCount;

/*
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `original_id` int(11) NOT NULL UNIQUE COMMENT '目标站点的文章id',
            `title` varchar (80) NOT NULL DEFAULT '' COMMENT '标题',
            `summary` varchar (500) NOT NULL DEFAULT '' COMMENT '摘要',
            `content` text NULL COMMENT '内容',
            `author` varchar(10) NULL COMMENT '作者',
            `create_time` datetime NULL COMMENT '创建时间',
            `publish_time` datetime NULL COMMENT '发布时间',
            `starred` enum('yes', 'no') NOT NULL DEFAULT 'no' COMMENT '是否推荐',
            `comment` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
            `read` int(11) NOT NULL DEFAULT 0 COMMENT '阅读数',*/
}
