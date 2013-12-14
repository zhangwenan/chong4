package com.wenlie.chong4.service;


import com.wenlie.chong4.bean.AlimamaItem;
import com.wenlie.chong4.bean.KeywordItemId;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-12-3
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public interface KeywordItemIdService {


    Boolean add(KeywordItemId keywordItemId);

    Boolean batchAdd(List<KeywordItemId> keywordItemIds);

    Boolean batchAdd(String keyword, List<AlimamaItem> items);



    /*Boolean update(KeywordItemId keywordItemId);*/

    List<KeywordItemId> getList(String keyword);

}
