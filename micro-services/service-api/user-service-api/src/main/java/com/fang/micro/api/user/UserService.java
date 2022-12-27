package com.fang.micro.api.user;

import com.fang.micro.api.user.pojo.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/12/26 21:29
 */
@WebService(targetNamespace = "http://user.impl.micro.fang.com/")
public interface UserService {

    @WebMethod(operationName = "getUserInfo")
    User getUserInfo();
}
