package com.wenlie.chong4.service.impl;

import com.wenlie.chong4.bean.SiteConfig;
import com.wenlie.chong4.bean.SysConfig;
import com.wenlie.chong4.service.SiteConfigService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-17
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class SiteConfigServicImpl implements SiteConfigService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public SiteConfig getConfigByServerName(String domain) {
        return sqlSession.selectOne("SiteConfigMapper.getConfigByServerName", domain);
    }

    @Override
    public void updateConfig(SiteConfig siteConfig) {
        sqlSession.update("SiteConfigMapper.updateConfig", siteConfig);
    }
}
