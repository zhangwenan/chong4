package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Article;
import com.wenlie.chong4.bean.RefArticle;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface RefArticleService {


    // 添加
    void add(RefArticle refArticle);

    // 批量添加
    void batchAdd(List<RefArticle> refArticles);

    // 删除
    void deleteById(int id);

    // 根据文章id 删除
    void deleteByArticleId(int articleId);

    // 根据相关文章id删除
    void deleteByRefArticleId(int refArticleId);


    // 根据 文章id，查询相关推荐的文章
    List<Integer> getRefArticleIds(Article article);
}
