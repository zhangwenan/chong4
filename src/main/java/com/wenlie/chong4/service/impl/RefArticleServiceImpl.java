package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.RefArticle;
import com.wenlie.chong4.service.RefArticleService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class RefArticleServiceImpl implements RefArticleService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Boolean existed(RefArticle refArticle) {
        if(getByArticleIdAndRefArticleId(refArticle) != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @Override
    public void add(RefArticle refArticle) {
        sqlSession.insert("RefArticle.add", refArticle);
    }

    @Override
    public void batchAdd(List<RefArticle> refArticles) {
        sqlSession.insert("RefArticle.batchAdd", refArticles);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("RefArticle.deleteById", id);
    }

    @Override
    public void deleteByArticleId(int articleId) {
        sqlSession.delete("RefArticle.deleteByArticleId", articleId);
    }

    @Override
    public void deleteByRefArticleId(int refArticleId) {
        sqlSession.delete("RefArticle.deleteByRefArticleId", refArticleId);
    }

    @Override
    public List<Integer> getRefArticleIdsByArticleId(int articleId) {
        return sqlSession.selectList("RefArticle.getRefArticleIdsByArticleId", articleId);
    }

    @Override
    public RefArticle getByArticleIdAndRefArticleId(RefArticle refArticle) {
        return sqlSession.selectOne("RefArticle.getByArticleIdAndRefArticleId", refArticle);
    }
}
