package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class ArticleTag {


    private int id;

    // 标签id
    private int tagId;

    // 文章原始id
    private int articleId;

    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `tag_id` int(11) NOT NULL COMMENT '标签id',
            `original_id` int(11) NOT NULL COMMENT '文章原始id',*/
}
