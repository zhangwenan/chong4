package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.AlimamaItem;
import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.service.AlimamaItemService;
import com.wenlie.chong4.service.KeywordService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public class AlimamaItemServiceImpl implements AlimamaItemService {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public AlimamaItem getByItemId(long itemId) {
        return sqlSession.selectOne("AlimamaItem.getByItemId", itemId);
    }



    @Override
    public Boolean add(AlimamaItem alimamaItem) {
        if (!itemIdExist(alimamaItem.getItemId())){
            sqlSession.insert("AlimamaItem.add", alimamaItem);
        }
        return true;
    }

    @Override
    public Boolean batchAdd(List<AlimamaItem> alimamaItemList) {
        /*sqlSession.insert("AlimamaItem.batchAdd", alimamaItemList);*/
        for(int i=0; i<alimamaItemList.size(); i++){
            add(alimamaItemList.get(i));
        }
        return true;
    }

    @Override
    public void update(AlimamaItem alimamaItem) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean itemIdExist(long itemId) {
        int count = sqlSession.selectOne("AlimamaItem.countItemId", itemId);
        return count>0?Boolean.TRUE:Boolean.FALSE;
    }
}
