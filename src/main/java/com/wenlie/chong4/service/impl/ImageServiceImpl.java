package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.Image;
import com.wenlie.chong4.service.ImageService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-20.
 */
public class ImageServiceImpl implements ImageService {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public void add(Image image) {

    }

    @Override
    public void batchAdd(List<Image> images) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByUrl(String url) {

    }

    @Override
    public void update(Image image) {

    }

    @Override
    public Image getById(int id) {
        return null;
    }

    @Override
    public Image getByUrl(String url) {
        return null;
    }
}
