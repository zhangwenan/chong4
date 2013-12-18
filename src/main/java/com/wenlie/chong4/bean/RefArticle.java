package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class RefArticle {


    private int id;

    // 文章原始id
    private int articleId;

    // 相关文章的原始id
    private int refArticleId;


    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `original_id` int(11) NOT NULL COMMENT '文章原始id',
            `ref_original_id` int(11) NOT NULL COMMENT '相关推荐的文章原始id',*/
}
