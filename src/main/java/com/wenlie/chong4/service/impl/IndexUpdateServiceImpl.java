package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.IndexUpdate;
import com.wenlie.chong4.service.IndexUpdateService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-28
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class IndexUpdateServiceImpl implements IndexUpdateService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public IndexUpdate getOne() {
        return sqlSession.selectOne("IndexUpdate.getOne");
    }

    @Override
    public void update(IndexUpdate indexUpdate) {
        sqlSession.update("IndexUpdate.update", indexUpdate);
    }


}
