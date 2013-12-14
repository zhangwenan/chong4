package com.wenlie.chong4.module.screen.main;

import com.alibaba.citrus.turbine.Context;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class Menu {



    @Autowired
    HttpServletRequest request;

    public void execute(Context context) throws Exception{
        context.put("pageId", "menu");
    }
}
