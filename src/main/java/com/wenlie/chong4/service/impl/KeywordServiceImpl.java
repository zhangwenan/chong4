package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.service.KeywordService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Keyword getKeywordByAlias(String alias) {
        return sqlSession.selectOne("Keyword.getByAlias", alias);
    }

    @Override
    public List<Keyword> getNextList(int from, int limit) {
        Map m = new HashMap();
        m.put("from", from);
        m.put("limit", limit);
        return sqlSession.selectList("Keyword.getNextList", m);
    }

    @Override
    public List<Keyword> getPrevList(int from, int limit) {
        Map m = new HashMap();
        m.put("from", from);
        m.put("limit", limit);
        return sqlSession.selectList("Keyword.getPrevList", m);
    }

    @Override
    public Keyword getLastKeyword() {
        return sqlSession.selectOne("Keyword.getLastKeyword");
    }

    @Override
    public List<Keyword> getKeywordsHasNoItems(int length) {
        return sqlSession.selectList("Keyword.getKeywordsHasNoItems", length);
    }

    @Override
    public int count() {
        return sqlSession.selectOne("Keyword.count");
    }

    @Override
    public void update(Keyword keyword) {
        sqlSession.update("Keyword.update", keyword);
    }


}
