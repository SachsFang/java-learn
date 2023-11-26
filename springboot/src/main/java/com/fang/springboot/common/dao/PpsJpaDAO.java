package com.fang.springboot.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author shaobin
 * @date 2023/11/24 11:04
 */
@NoRepositoryBean
public interface PpsJpaDAO<T, ID> extends JpaRepository<T, ID> {
}
