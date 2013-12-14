package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.AlimamaItem;
import com.wenlie.chong4.bean.KeywordItemId;
import com.wenlie.chong4.service.KeywordItemIdService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-12-3
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public class KeywordItemIdServiceImpl implements KeywordItemIdService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Boolean add(KeywordItemId keywordItemId) {
        sqlSession.insert("KeywordItemId.add", keywordItemId);
        return true;
    }

    @Override
    public Boolean batchAdd(List<KeywordItemId> keywordItemIds) {
        /*sqlSession.insert("KeywordItemId.batchAdd", keywordItemIds);*/
        return true;
    }

    @Override
    public Boolean batchAdd(String keyword, List<AlimamaItem> items) {
        List<KeywordItemId> keywordItemIds = new ArrayList<KeywordItemId>();
        for(int i=0; i<items.size(); i++){
            keywordItemIds.add(new KeywordItemId(keyword, items.get(i).getItemId()));
        }
        sqlSession.insert("KeywordItemId.batchAdd", keywordItemIds);
        return true;
    }

    @Override
    public List<KeywordItemId> getList(String keyword) {
        return sqlSession.selectList("KeywordItemId.getList", keyword);
    }
}
