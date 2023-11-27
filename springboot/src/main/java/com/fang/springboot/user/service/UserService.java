package com.fang.springboot.user.service;

import com.fang.springboot.user.enums.SexEnum;
import com.fang.springboot.user.pojo.UserPO;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/8/5 20:04
 */
public interface UserService {

    String printInfo();

    int jpaInsertUser(String name, Integer age);

    int jpaInsertUser(String name, Integer age, Integer sex);

    int myBatisInsertUser(String name, Integer age, SexEnum sex);

    List<UserPO> myBatisQueryUserList(SexEnum sex);

    String testSpringAnnotationTransactional();

    String testManualTransactional();

    String testMyTransactional();
}
