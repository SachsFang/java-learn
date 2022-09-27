package com.fang.spring.pojo;

import com.fang.spring.user.pojo.User;
import com.fang.spring.user.serviceImpl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shaobin
 * @date 2022/9/4 16:35
 */
public class IOCInjectionTest {
    public static void main(String[] args) {
        // 加载Spring的配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("IOCInjection.xml");
        // setter注入属性
        User setterInjectionParamUser = applicationContext.getBean("setterInjectionParamUser", User.class);
        // 构造器注入属性例子
//        User constructInjectionParamUser = applicationContext.getBean("constructInjectionParamUser", User.class);
//        System.out.println(constructInjectionParamUser + "生命周期：使用对象");
        // 属性注入IOC容器对象例子
//        UserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
//        userServiceImpl.addUser();
//        System.out.println(userServiceImpl.getMyUserDao().getName());
//        System.out.println(userServiceImpl);
        // 自动装配例子
        UserServiceImpl autoUserServiceImpl = applicationContext.getBean("autoUserServiceImpl", UserServiceImpl.class);
        autoUserServiceImpl.addUser();
        System.out.println(autoUserServiceImpl);
        // 关闭 spring 上下文
        applicationContext.close();
    }
}
