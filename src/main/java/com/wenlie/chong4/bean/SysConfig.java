package com.wenlie.chong4.bean;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-16
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */
@Data
public class SysConfig {

    private int id;

    /**
     * 配置名
     */
    private String name;

    /**
     * 配置值
     */
    private String value;
}
