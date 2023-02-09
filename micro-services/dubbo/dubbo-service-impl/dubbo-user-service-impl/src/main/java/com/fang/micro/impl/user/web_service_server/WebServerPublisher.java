package com.fang.micro.impl.user.web_service_server;

import com.fang.micro.impl.user.UserServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author shaobin
 * @date 2022/11/5 12:13
 */
public class WebServerPublisher {
    public static void main(String[] args) {
        // WebService 技术发布服务
        Endpoint.publish("http://127.0.0.1:8099/service/userServiceImpl", new UserServiceImpl());
    }
}
