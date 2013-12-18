package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public void add(Article article) {
        sqlSession.insert("Article.add", article);
    }

    @Override
    public void batchAdd(List<Article> articles) {
        sqlSession.insert("Article.batchAdd", articles);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("Article.deleteById", id);
    }


    @Override
    public void update(Article article) {
        sqlSession.update("Article.update", article);
    }

    @Override
    public Article getById(int id) {
        return sqlSession.selectOne("Article.getById", id);
    }


}
