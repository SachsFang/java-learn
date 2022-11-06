package com.fang.user.web_server_client;

import com.fang.springboot.user.service.UserService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author shaobin
 * @date 2022/11/5 15:14
 */
public class WebServiceClient {

    public static String serviceUrl = "http://serviceimpl.user.springboot.fang.com/";

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8090/service/userServiceImpl?wsdl");
        QName serviceQName = new QName(serviceUrl, "UserServiceImplService");
        Service service = Service.create(url, serviceQName);
        QName portQName = new QName(serviceUrl, "UserServiceImplPort");
        UserService userService = service.getPort(portQName, UserService.class);
        userService.printInfo();
        System.out.println("client call service success");
    }
}
