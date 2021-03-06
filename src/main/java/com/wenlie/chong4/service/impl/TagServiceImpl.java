package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.Tag;
import com.wenlie.chong4.service.TagService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wenlie on 13-12-18.
 */
public class TagServiceImpl implements TagService {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public Boolean existed(String tagName) {
        if(getByName(tagName) != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @Override
    public void add(Tag tag) {
        if(!existed(tag.getName())){
            sqlSession.insert("Tag.add", tag);
        }
    }

    @Override
    public void batchAdd(List<Tag> tags) {
        for (int i=0; i< tags.size(); i++){
            add(tags.get(i));
        }
        /*sqlSession.insert("Tag.batchAdd", tags);*/
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("Tag.deleteById", id);
    }

    @Override
    public void deleteByName(String name) {
        sqlSession.delete("Tag.deleteByName", name);
    }

    @Override
    public void update(Tag tag) {
        sqlSession.update("Tag.update", tag);
    }

    @Override
    public Tag getById(int id) {
        return sqlSession.selectOne("Tag.getById", id);
    }

    @Override
    public Tag getByName(String name) {
        return sqlSession.selectOne("Tag.getByName", name);
    }
}
