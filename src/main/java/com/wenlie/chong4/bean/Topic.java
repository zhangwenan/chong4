package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created by wenlie on 13-12-17.
 */
@Data
public class Topic {

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }

    // 主题id
    private int id;

    // 主题名称
    private String name;

    /*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
            `name` varchar (80) NOT NULL DEFAULT '' COMMENT '主题名称',*/
}
