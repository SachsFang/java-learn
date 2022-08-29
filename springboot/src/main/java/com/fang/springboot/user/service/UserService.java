package com.fang.springboot.user.service;

/**
 * @author shaobin
 * @date 2022/8/5 20:04
 */
public interface UserService {
    int insertUser(String name, Integer age);

    String testSpringAnnotationTransactional();

    String testManualTransactional();

    String testMyTransactional();
}
