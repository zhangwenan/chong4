package com.wenlie.chong4.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.wenlie.chong4.bean.*;
import com.wenlie.chong4.exception.KeywordNotFoundException;
import com.wenlie.chong4.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class Detail {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KeywordItemIdService keywordItemIdService;

    @Autowired
    private IndexUpdateService indexUpdateService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private AlimamaItemService alimamaItemService;

    @Autowired
    HttpServletRequest request;

    public void execute(Context context,@Param("alias") String alias) throws Exception{
        context.put("isDetail", "true");

        if(!SettingContext.containsKey("siteTitle")){
            SettingContext.put("siteTitle", configService.getByConfigName("siteTitle"));
            SettingContext.put("siteDescription", configService.getByConfigName("siteDescription"));
            SettingContext.put("taodianjinPid", configService.getByConfigName("taodianjinPid"));
            SettingContext.put("searchPid", configService.getByConfigName("searchPid"));
            SettingContext.put("wordTitle", configService.getByConfigName("wordTitle"));
            SettingContext.put("wordWelcome", configService.getByConfigName("wordWelcome"));
        }

        context.put("taodianjinPid", SettingContext.get("taodianjinPid"));
        Keyword keyword = keywordService.getKeywordByAlias(alias);
        if(keyword == null){
            throw new KeywordNotFoundException("别名：" + alias + "没有找到");
        }

        String wordTitle = (String)SettingContext.get("wordTitle");
        String wordWelcome = (String)SettingContext.get("wordWelcome");
        context.put("pageTitle", wordTitle.replace("{{ word }}", keyword.getName()));
        context.put("pageDescription", wordWelcome.replace("{{ word }}", keyword.getName()));
        context.put("keyword", keyword);


        context.put("guessList", keywordService.getNextList(keyword.getId(), 10));
        context.put("newList", keywordService.getNextList(keyword.getId(), 40));
        context.put("hotList", keywordService.getPrevList(keyword.getId(), 30));

        List<KeywordItemId> keywordItemIds = keywordItemIdService.getList(keyword.getName());
        List<AlimamaItem> alimamaItems = new ArrayList<AlimamaItem>();
        for(int i=0; i<keywordItemIds.size(); i++){
            long itemId = keywordItemIds.get(i).getItemId();
            AlimamaItem alimamaItem = alimamaItemService.getByItemId(itemId);
            alimamaItems.add(alimamaItem);
        }
        context.put("alimamaItems", alimamaItems);

    }
}
