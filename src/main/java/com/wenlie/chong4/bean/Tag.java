package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class Tag {


    private int id;

    private String name;


    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `name` int(11) NOT NULL UNIQUE COMMENT '标签名',*/
}
