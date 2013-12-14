package com.wenlie.chong4.module.screen;

import com.alibaba.citrus.turbine.Context;

import com.wenlie.chong4.bean.IndexUpdate;
import com.wenlie.chong4.bean.Keyword;
import com.wenlie.chong4.bean.SettingContext;
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
    private KeywordService keywordService;

    @Autowired
    private IndexUpdateService indexUpdateService;

    @Autowired
    private ConfigService configService;

    @Autowired
    HttpServletRequest request;

    public void execute(Context context) throws Exception{
        context.put("isIndex", "true");

        if(!SettingContext.containsKey("siteTitle")){
            SettingContext.put("siteTitle", configService.getByConfigName("siteTitle"));
            SettingContext.put("siteDescription", configService.getByConfigName("siteDescription"));
            SettingContext.put("taodianjinPid", configService.getByConfigName("taodianjinPid"));
            SettingContext.put("searchPid", configService.getByConfigName("searchPid"));
            SettingContext.put("wordWelcome", configService.getByConfigName("wordWelcome"));
            SettingContext.put("wordTitle", configService.getByConfigName("wordTitle"));
        }


        context.put("pageTitle", SettingContext.get("siteTitle"));
        context.put("pageDescription", SettingContext.get("siteDescription"));



        if(!SettingContext.containsKey("indexUpdate")){
            int count = keywordService.count();
            Keyword lastKeyword = keywordService.getLastKeyword();
            SettingContext.put("count", count);
            SettingContext.put("lastKeyword", lastKeyword);

            IndexUpdate indexUpdateOri = indexUpdateService.getOne();
            int from = indexUpdateOri.getBeginId()>=lastKeyword.getId() ? 0: indexUpdateOri.getBeginId();
            List<Keyword> keywords = keywordService.getNextList(from, 300);
            int end = keywords.get(keywords.size()-1).getId();
            Date now = new Date();
            IndexUpdate indexUpdate = new IndexUpdate(from,end,now);

            SettingContext.put("indexUpdate", indexUpdate);
            SettingContext.put("indexKeywordList", keywords);

            context.put("keywordList", keywords);
            context.put("indexUpdate", indexUpdate);
            indexUpdateService.update(indexUpdate);
        }
        else{
            IndexUpdate indexUpdate = (IndexUpdate)SettingContext.get("indexUpdate");
            Date now = new Date();
            if(now.getTime() - indexUpdate.getLastUpdateTime().getTime() > 60*60*1000){
                int count = (Integer)SettingContext.get("count");
                Keyword lastKeyword = (Keyword)SettingContext.get("lastKeyword");
                int from = indexUpdate.getEndId()>=lastKeyword.getId()?0:indexUpdate.getEndId();
                List<Keyword> keywords = keywordService.getNextList(from, 300);

                int end = keywords.get(keywords.size()-1).getId();

                indexUpdate.setBeginId(from);
                indexUpdate.setEndId(end);
                indexUpdate.setLastUpdateTime(now);

                SettingContext.put("indexUpdate", indexUpdate);
                SettingContext.put("indexKeywordList", keywords);

                context.put("keywordList", keywords);
                indexUpdateService.update(indexUpdate);
            }
            else{
                context.put("keywordList", SettingContext.get("indexKeywordList"));
            }
            context.put("indexUpdate", indexUpdate);
        }
    }
}
