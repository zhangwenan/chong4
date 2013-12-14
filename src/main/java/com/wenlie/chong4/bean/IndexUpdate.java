package com.wenlie.chong4.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-28
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class IndexUpdate {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int beginId;

    @Getter
    @Setter
    private int endId;

    @Getter
    @Setter
    private Date lastUpdateTime;

    public IndexUpdate() {
    }

    public IndexUpdate(int beginId, int endId, Date lastUpdateTime) {
        this.beginId = beginId;
        this.endId = endId;
        this.lastUpdateTime = lastUpdateTime;
    }
}
