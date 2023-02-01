package com.fang.web.controller;

import com.fang.micro.api.order.pojo.Order;
import com.fang.web.feign.OrderServiceFeign;
import com.fang.web.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2022/12/30 17:11
 */
@RestController
@RequestMapping("/index")
@RefreshScope //Nocos config 的注解，配置可在项目启动时刷新配置文件
public class IndexController {

    @Value("${author.name}")
    private String authorName;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private OrderServiceFeign orderServiceFeign;

    /**
     * 通过nacos配置中心获取配置文件信息
     * @return
     */
    @RequestMapping("/getAuthorNameByNacosConfig")
    public String getAuthorNameByNacosConfig() {
        return authorName;
    }

    /**
     * 分布式事务测试
     */
    @Transactional
    @RequestMapping("/distributedTransactional")
    public void distributedTransactional() {
        userServiceFeign.insertUser("distributedTransactional", 20);
        Order order = new Order();
        order.setCode("111");
        order.setProductId("01");
        order.setAmount(1);
        order.setRemark("test");
        orderServiceFeign.insertOrder(order);
        int i = 1 / 0;
    }
}
