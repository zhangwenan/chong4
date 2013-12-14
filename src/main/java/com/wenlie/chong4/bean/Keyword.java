package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-12
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Keyword {

    private int id;

    // 关键词名称
    private String name;

    // 关键词别名
    private String alias;

    // 是否已经采集了商品
    private String hasItems;
}
