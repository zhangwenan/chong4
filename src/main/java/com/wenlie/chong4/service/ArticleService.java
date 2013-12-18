package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface ArticleService {


    // 添加文章
    void add(Article article);

    // 批量添加文章
    void batchAdd(List<Article> articles);

    // 删除对应id 的文章
    void deleteById(int id);

    // 更新文章
    void update(Article article);

    // 根据id查询
    Article getById(int id);



}
