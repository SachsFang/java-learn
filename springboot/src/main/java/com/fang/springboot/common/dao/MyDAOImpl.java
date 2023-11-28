package com.fang.springboot.common.dao;

/**
 * @author shaobin
 * @date 2023/11/28 15:08
 */
public class MyDAOImpl<T> implements MyDAO<T> {

    @Override
    public void saveOrUpdateBatch() {
        //todo 公共dao
        System.out.println("good");
    }
}
