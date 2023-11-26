package com.fang.springboot.user.service;

import com.fang.springboot.user.pojo.UserPO;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/8/5 20:04
 */
public interface UserService {

    String printInfo();

    int jpaInsertUser(String name, Integer age);

    List<UserPO> myBatisQueryUserList(String id);

    String testSpringAnnotationTransactional();

    String testManualTransactional();

    String testMyTransactional();
}
