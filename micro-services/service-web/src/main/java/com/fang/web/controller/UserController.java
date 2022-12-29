package com.fang.web.controller;

import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import com.fang.web.my_load_balance.LoadBalanceStrategy;
import com.fang.web.my_load_balance.RandomLoadBalanceStrategy;
import com.fang.web.my_load_balance.RoundLoadBalanceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/27 11:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static String serviceUrl = "http://user.impl.micro.fang.com/";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("getUerInfoByNacosProvider")
    public User getUerInfoByNacosProvider() {
        // 根据服务名称从服务中心获取接口地址集群列表
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("user-micro-service");
        /** 本地负载均衡 **/
        // 使用自定义的负载均衡策略
        LoadBalanceStrategy loadBalanceStrategy = new RoundLoadBalanceStrategy();
//        LoadBalanceStrategy loadBalanceStrategy = new RandomLoadBalanceStrategy();
//        ServiceInstance serviceInstance = loadBalanceStrategy.getSingleAddress(serviceInstanceList);
        // 使用spring cloud封装好的LoadBalancerClient Ribbon负载均衡工具类
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-micro-service");
        // 获取IP和端口
        URI uri = serviceInstance.getUri();
        /** 请求服务 **/
        // 使用httpClient请求接口
        return restTemplate.getForObject(uri + "/learn/user/getUserInfo", User.class);
    }

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
