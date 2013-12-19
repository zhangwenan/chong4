package com.wenlie.chong4.module.control;

import com.alibaba.citrus.turbine.Context;
import com.wenlie.chong4.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-28
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public class Footer {



    public void execute(Context context){
        /*if(!context.containsKey("siteStatus")){
            context.put("siteStatus", configService.getByConfigName("siteStatus"));
        }*/
    }
}
