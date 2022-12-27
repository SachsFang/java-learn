package com.fang.springboot.user.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/8/5 20:04
 */
public interface UserService {

    String printInfo();

    int insertUser(String name, Integer age);

    String testSpringAnnotationTransactional();

    String testManualTransactional();

    String testMyTransactional();
}
