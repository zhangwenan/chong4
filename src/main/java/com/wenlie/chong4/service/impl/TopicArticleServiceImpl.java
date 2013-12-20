package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.TopicArticle;
import com.wenlie.chong4.service.TopicArticleService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class TopicArticleServiceImpl implements TopicArticleService {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public Boolean exist(TopicArticle topicArticle) {
        TopicArticle topicArticle1 = sqlSession.selectOne("TopicArticle.getByTopicIdAndArticleId", topicArticle);

        if(topicArticle1 != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @Override
    public void add(TopicArticle topicArticle) {

        if(!exist(topicArticle)){
            sqlSession.insert("TopicArticle.add", topicArticle);
        }
    }

    @Override
    public void batchAdd(List<TopicArticle> topicArticles) {
        for (int i=0; i<topicArticles.size(); i++){
            add(topicArticles.get(i));
        }
        /*sqlSession.insert("TopicArticle.batchAdd", topicArticles);*/
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("TopicArticle.deleteById", id);
    }

    @Override
    public void deleteByTopicId(int topicId) {
        sqlSession.delete("TopicArticle.deleteByTopicId", topicId);
    }

    @Override
    public void deleteByArticleId(int articleId) {
        sqlSession.delete("TopicArticle.deleteByArticleId", articleId);
    }

    @Override
    public void update(TopicArticle topicArticle) {
        sqlSession.update("TopicArticle.update", topicArticle);
    }

    @Override
    public TopicArticle getById(int id) {
        return sqlSession.selectOne("TopicArticle.getById", id);
    }

    @Override
    public List<Integer> getArticleIdsByTopicId(int topicId) {
        return sqlSession.selectList("TopicArticle.getArticleIdsByTopicId", topicId);
    }
}
