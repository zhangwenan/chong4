package com.wenlie.chong4.service;

import com.wenlie.chong4.bean.Image;
import com.wenlie.chong4.bean.Tag;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public interface ImageService {


    // 添加
    void add(Image image);

    // 批量添加
    void batchAdd(List<Image> images);

    // 删除对应id 的图片
    void deleteById(int id);

    // 删除对应URL 的图片
    void deleteByUrl(String url);

    // 更新
    void update(Image image);

    // 根据id查询
    Image getById(int id);

    // 根据Url查询
    Image getByUrl(String url);

}
