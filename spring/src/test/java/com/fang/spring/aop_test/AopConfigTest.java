package com.fang.spring.aop_test;

import com.fang.spring.user.pojo.User;
import com.fang.spring.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring使用的是JDK动态代理还是CGKLIB动态代理Debugger
 * @author shaobin
 * @date 2022/9/18 10:57
 */
public class AopConfigTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("AopConfig.xml");
        // 代理有实现接口的目标类使用的是JDK动态代理
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.addUser();
        // 代理没有实现接口的目标类使用的是CGLIB动态代理
        User user = context.getBean("user", User.class);
    }
}
