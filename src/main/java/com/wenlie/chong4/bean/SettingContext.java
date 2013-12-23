package com.wenlie.chong4.bean;


import com.wenlie.chong4.job.Chong4ListJob;
import com.wenlie.chong4.job.TaokeItemJob;
import com.wenlie.chong4.service.KeywordItemIdService;
import com.wenlie.chong4.service.KeywordService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-28
 * Time: 下午7:18
 * To change this template use File | Settings | File Templates.
 */
public class SettingContext implements InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(SettingContext.class); // 日志

    public static Map<String, Object> settingMap = new ConcurrentHashMap<String, Object>();


    public static Boolean containsKey(String key){
        return settingMap.containsKey(key);
    }

    public static void put(String key, Object value){
        settingMap.put(key,value);
    }

    public static Object get(String key){
        return settingMap.get(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
