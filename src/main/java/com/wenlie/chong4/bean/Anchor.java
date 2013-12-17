package com.wenlie.chong4.bean;

/**
 * Created by wenlie on 13-12-17.
 */
public class Anchor {


    private int id;


    private int originalId;

    private long itemId;

    private String reTaoLink;

    private String directTaoLink;

    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `original_id` varchar (20) NOT NULL UNIQUE COMMENT '锚点id',
            `item_id` varchar (20) NULL COMMENT '锚点指向的itemId',
            `re_tao_link` varchar (500) NULL COMMENT '指向淘宝热卖的url',
            `direct_tao_link` varchar (500) NULL COMMENT '直接跳转到宝贝详情的Url',*/
}
