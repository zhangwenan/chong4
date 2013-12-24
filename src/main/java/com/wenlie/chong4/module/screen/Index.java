package com.wenlie.chong4.module.screen;

import com.alibaba.citrus.turbine.Context;

import com.wenlie.chong4.bean.IndexUpdate;
import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.bean.SettingContext;
import com.wenlie.chong4.job.Chong4ListJob;
import com.wenlie.chong4.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class Index {


    @Autowired
    private Chong4ListJob chong4ListJob;



    @Autowired
    HttpServletRequest request;

    public void execute(Context context) throws Exception{
        if(!SettingContext.containsKey("isCollecting") || !(Boolean)SettingContext.get("isCollecting")){
            SettingContext.put("isCollecting", Boolean.TRUE);
            // chong4ListJob.execute();
            // chong4ListJob.dealTags();
        }

    }
}
