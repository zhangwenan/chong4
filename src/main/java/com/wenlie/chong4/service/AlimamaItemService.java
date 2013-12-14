package com.wenlie.chong4.service;


import com.wenlie.chong4.bean.AlimamaItem;
import com.wenlie.chong4.bean.Keyword;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface AlimamaItemService {

    /**
     * 根据itemId查询商品信息
     * @param itemId
     * @return
     */
    AlimamaItem getByItemId(long itemId);


    /**
     * 添加
     * @param alimamaItem
     * @return
     */
    Boolean add(AlimamaItem alimamaItem);


    /**
     * 批量添加
     * @param alimamaItemList
     * @return
     */
    Boolean batchAdd(List<AlimamaItem> alimamaItemList);


    void update(AlimamaItem alimamaItem);


    Boolean itemIdExist(long itemId);

}
