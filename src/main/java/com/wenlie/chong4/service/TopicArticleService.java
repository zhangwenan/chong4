package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.Topic;
import com.wenlie.chong4.bean.TopicArticle;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface TopicArticleService {


    // 添加
    void add(TopicArticle topicArticle);

    // 批量添加
    void batchAdd(List<TopicArticle> topicArticles);


    // 根据id删除
    void deleteById(int id);


    // 根据topic_id 删除
    void deleteByTopicId(int topicId);

    // 根据article_id 删除
    void deleteByArticleId(int articleId);


    // 更新
    void update(TopicArticle topicArticle);

    // 根据id 查询
    TopicArticle getById(int id);


    // 根据 topic_id 查询文章
    List<Integer> getArticleIdsByTopicId(int topicId);
}
