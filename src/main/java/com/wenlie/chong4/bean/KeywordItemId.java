package com.wenlie.chong4.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-12-3
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */

public class KeywordItemId {
    @Getter
    @Setter
    private int id;


    @Getter
    @Setter
    private String keyword;


    @Getter
    @Setter
    private long itemId;

    public KeywordItemId() {
    }

    public KeywordItemId(String keyword, long itemId) {
        this.keyword = keyword;
        this.itemId = itemId;
    }
}
