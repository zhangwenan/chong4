package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Anchor;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface AnchorService {

    // 添加链接
    void add(Anchor anchor);

    // 批量添加链接
    void batchAdd(List<Anchor> anchors);

    // 删除链接
    void deleteById(int id);

    // 根据原始的链接删除
    void deleteByArticleId(int articleId);

    // 根据链接的key 来删除
    void deleteByAnchorKey(String anchorKey);

    // 更新
    void update(Anchor anchor);

    // 根据id 获取
    Anchor getById(int id);

    // 根据链接的key获取
    Anchor getByAnchorKey(String anchorKey);



}
