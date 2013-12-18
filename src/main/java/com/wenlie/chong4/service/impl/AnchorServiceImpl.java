package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.Anchor;
import com.wenlie.chong4.service.AnchorService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class AnchorServiceImpl implements AnchorService {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public void add(Anchor anchor) {
        sqlSession.insert("Anchor.add", anchor);
    }

    @Override
    public void batchAdd(List<Anchor> anchors) {
        sqlSession.insert("Anchor.batchAdd", anchors);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("Anchor.deleteById", id);
    }

    @Override
    public void deleteByArticleId(int articleId) {
        sqlSession.delete("Anchor.deleteByArticleId", articleId);
    }

    @Override
    public void deleteByAnchorKey(String anchorKey) {
        sqlSession.delete("Anchor.deleteByAnchorKey", anchorKey);
    }

    @Override
    public void update(Anchor anchor) {
        sqlSession.update("Anchor.update", anchor);
    }

    @Override
    public Anchor getById(int id) {
        return sqlSession.selectOne("Anchor.getById", id);
    }

    @Override
    public Anchor getByAnchorKey(String anchorKey) {
        return sqlSession.selectOne("Anchor.getByAnchorKey", anchorKey);
    }
}
