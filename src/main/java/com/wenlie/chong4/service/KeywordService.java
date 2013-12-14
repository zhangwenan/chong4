package com.wenlie.chong4.service;


import com.wenlie.chong4.bean.Keyword;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface KeywordService {

    /**
     * 通过别名查询关键词
     * @param alias
     * @return
     */
    Keyword getKeywordByAlias(String alias);


    /**
     * 从from位置开始，随机获取 limit 个关键词
     * @param from   开始id
     * @param limit   获取数量
     * @return
     */
    List<Keyword> getNextList(int from, int limit);


    List<Keyword> getPrevList(int from, int limit);

    Keyword getLastKeyword();


    List<Keyword> getKeywordsHasNoItems(int length);

    /**
     * 统计关键词个数
     * @return
     */
    int count();


    void update(Keyword keyword);

}
