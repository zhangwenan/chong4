package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.Tag;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface TagService {


    // 是否存在
    Boolean existed(String tagName);

    // 添加标签
    void add(Tag tag);

    // 批量添加标签
    void batchAdd(List<Tag> tags);

    // 删除对应id 的标签
    void deleteById(int id);

    // 删除对应名称的标签
    void deleteByName(String name);

    // 更新
    void update(Tag tag);

    // 根据id查询
    Tag getById(int id);

    // 根据标签名称查询
    Tag getByName(String name);



}
