package com.fang.springboot.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author shaobin
 * @date 2023/11/24 11:04
 */
@NoRepositoryBean
public interface PpsJpaDAO<T> extends BaseMapper<T>, MyDAO<T> {
}
