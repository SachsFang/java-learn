package com.fang.springboot.common.dao;

import com.baomidou.mybatisplus.core.mapper.Mapper;

/**
 * @author shaobin
 * @date 2023/11/28 15:11
 */
public interface MyDAO<T> extends Mapper<T> {

    void saveOrUpdateBatch();

}
