package com.fang.springboot.user.web_service_server;

import com.fang.springboot.user.serviceimpl.UserServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author shaobin
 * @date 2022/11/5 12:13
 */
public class Server {
    public static void main(String[] args) {
        // WebService 技术发布服务
        Endpoint.publish("http://127.0.0.1:8090/service/userServiceImpl", new UserServiceImpl());
    }
}
