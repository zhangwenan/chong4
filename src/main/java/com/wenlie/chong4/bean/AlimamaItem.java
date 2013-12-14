package com.wenlie.chong4.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-12-3
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
public class AlimamaItem {

    private long id;

    private String itemTitle;

    private long itemId;



    public AlimamaItem() {
    }

    public AlimamaItem(long itemId, String itemTitle) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    @Override
    public String toString() {
        return Long.toString(itemId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
