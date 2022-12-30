package com.fang.micro.api.user;

import com.fang.micro.api.user.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/12/26 21:29
 */
@WebService(targetNamespace = "http://user.impl.micro.fang.com/")
@RequestMapping("/user") //@RequestMapping写在这里是为了能够让feign客户端继承调用；此接口的实现类因为是实现了此接口，所以这个路径也会一并继承过去
public interface UserService {

    @WebMethod(operationName = "getUserInfo")
    @RequestMapping("/getUserInfo")
    User getUserInfo();
}
