package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.Topic;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface TopicService {

    // 主题是否已经存在
    Boolean existed(String topicName);


    // 添加主题
    void add(Topic topic);

    // 批量添加
    void batchAdd(List<Topic> topics);


    // 根据id删除主题
    void deleteById(int id);


    // 根据名称删除主题
    void deleteByName(String name);

    // 更新主题
    void update(Topic topic);

    // 根据主题id查询
    Topic getById(int id);

    // 根据主题名称查询
    Topic getByName(String name);
}
