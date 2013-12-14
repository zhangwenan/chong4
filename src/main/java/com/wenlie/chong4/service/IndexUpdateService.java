package com.wenlie.chong4.service;


import com.wenlie.chong4.bean.IndexUpdate;
import com.wenlie.chong4.bean.Keyword;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface IndexUpdateService {

    /**
     * @return
     */
    IndexUpdate getOne();


    void update(IndexUpdate indexUpdate);

}
