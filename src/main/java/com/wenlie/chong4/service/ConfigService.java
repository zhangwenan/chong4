package com.wenlie.chong4.service;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-5
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigService {

    /**
     * 根据配置名，获取配置信息
     * @param configName
     * @return
     */
    String getByConfigName(String configName);
}
