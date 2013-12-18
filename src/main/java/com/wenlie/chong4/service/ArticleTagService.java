package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.ArticleTag;
import com.wenlie.chong4.bean.Tag;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface ArticleTagService {

    // 添加
    void add(ArticleTag articleTag);

    // 批量添加
    void batchAdd(List<ArticleTag> articleTags);


    // 根据id删除
    void deleteById(int id);

    // 根据 tagId 删除
    void deleteByTagId(int tagId);


    // 根据 originalId 删除
    void deleteByOriginalId(int originalId);


    // 根据 id 获取
    ArticleTag getById(int id);


    // 根据 tagId 获取
    ArticleTag getByTagId(int tagId);

    // 根据 originalId 获取
    ArticleTag getByOriginalId(int originalId);


    // 根据标签查询文章
    List<Article> getOriginalByTag(Tag tag);
}
