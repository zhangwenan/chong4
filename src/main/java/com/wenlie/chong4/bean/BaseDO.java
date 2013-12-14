package com.wenlie.chong4.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-5
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
public class BaseDO implements Serializable{


    private static final long serialVersionUID = -520249020584719656L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
