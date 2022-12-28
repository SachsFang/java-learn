package com.fang.web.controller;

import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author shaobin
 * @date 2022/12/27 11:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static String serviceUrl = "http://user.impl.micro.fang.com/";

    /**
     * WebService技术实现RPC远程调用服务
     * @return
     * @throws MalformedURLException
     */
    @RequestMapping("/gerUserInfoByWebServer")
    public User getUserInfoByWebServer() throws MalformedURLException {
        // 提供服务方通过发布wsdl文件注册到WebService目录，调用方服务通过WebService目录获取对应服务的wsdl文件信息进行服务的调用（http+xml的调用方式）
        URL url = new URL("http://127.0.0.1:8099/service/userServiceImpl?wsdl");
        QName serviceQName = new QName(serviceUrl, "UserServiceImplService");
        Service service = Service.create(url, serviceQName);
        QName portQName = new QName(serviceUrl, "UserServiceImplPort");
        UserService userService = service.getPort(portQName, UserService.class);
        System.out.println("client call service success");
        return userService.getUserInfo();
    }
}
