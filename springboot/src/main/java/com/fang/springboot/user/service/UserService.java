package com.fang.springboot.user.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/8/5 20:04
 */
@WebService(targetNamespace = "http://serviceimpl.user.springboot.fang.com/")
public interface UserService {

    @WebMethod(operationName = "printInfo")
    String printInfo();
    int insertUser(String name, Integer age);

    String testSpringAnnotationTransactional();

    String testManualTransactional();

    String testMyTransactional();
}
