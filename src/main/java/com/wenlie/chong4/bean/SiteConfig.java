package com.wenlie.chong4.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-17
 * Time: 下午1:58
 * To change this template use File | Settings | File Templates.
 */
@Data
public class SiteConfig {

    private int id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 网站名称
     */
    private String siteName;


    /**
     * 站点描述
     */
    private String siteDesc;

    /**
     *
     */
    private String listTitleTemplate;

    private String listDescTemplate;

    private Date lastUpdatedTime;

    private int lastUpdatedId;
}
