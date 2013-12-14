package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.service.ConfigService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-28
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public String getByConfigName(String configName) {
        return sqlSession.selectOne("Config.getByConfigName", configName);
    }
}
