package com.wenlie.chong4.service.impl;


import com.wenlie.chong4.bean.Topic;
import com.wenlie.chong4.service.TopicService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class TopicServiceImpl implements TopicService {


    @Autowired
    private SqlSession sqlSession;


    @Override
    public Boolean existed(String topicName) {
        if(getByName(topicName) != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @Override
    public void add(Topic topic) {
        sqlSession.insert("Topic.add", topic);
    }

    @Override
    public void batchAdd(List<Topic> topics) {
        for (int i=0; i<topics.size(); i++){
            add(topics.get(i));
        }
        /*sqlSession.insert("Topic.batchAdd", topics);*/
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("Topic.deleteById", id);
    }

    @Override
    public void deleteByName(String name) {
        sqlSession.delete("Topic.deleteByName", name);
    }

    @Override
    public void update(Topic topic) {
        sqlSession.update("Topic.update", topic);
    }

    @Override
    public Topic getById(int id) {
        return sqlSession.selectOne("Topic.getById", id);
    }

    @Override
    public Topic getByName(String name) {
        return sqlSession.selectOne("Topic.getByName", name);
    }
}
