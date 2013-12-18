package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.ArticleTag;
import com.wenlie.chong4.service.ArticleTagService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class ArticleTagServiceImpl implements ArticleTagService {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public void add(ArticleTag articleTag) {
        sqlSession.insert("ArticleTag.add", articleTag);
    }

    @Override
    public void batchAdd(List<ArticleTag> articleTags) {
        sqlSession.insert("ArticleTag.batchAdd", articleTags);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("ArticleTag.deleteById", id);
    }

    @Override
    public void deleteByTagId(int tagId) {
        sqlSession.delete("ArticleTag.deleteByTagId", tagId);
    }

    @Override
    public void deleteByArticleId(int articleId) {
        sqlSession.delete("ArticleTag.deleteByArticleId", articleId);
    }

    @Override
    public ArticleTag getById(int id) {
        return sqlSession.selectOne("ArticleTag.getById", id);
    }

    @Override
    public ArticleTag getByTagId(int tagId) {
        return sqlSession.selectOne("ArticleTag.getByTagId", tagId);
    }

    @Override
    public ArticleTag getByArticleId(int articleId) {
        return sqlSession.selectOne("ArticleTag.getByArticleId", articleId);
    }

    @Override
    public List<Integer> getArticleIdsByTagId(int tagId) {
        return sqlSession.selectList("ArticleTag.getArticleIdsByTagId", tagId);
    }
}
