package com.fang.spring.pojo;

import com.fang.spring.user.pojo.User;
import com.fang.spring.user.serviceImpl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shaobin
 * @date 2022/9/2 10:49
 */
public class SpringLifeCycleTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringLifeCycle.xml");
        // 根据bean的id获取对象例子
//        User user = applicationContext.getBean("user", User.class);
//        user.setName("fang");
//        user.setAge(22);
//        System.out.println(user);
        // setter注入生命周期
        User setterInjectionParamUser = applicationContext.getBean("setterInjectionParamUser", User.class);
        // 构造器注入属性例子生命周期
//        User constructInjectionParamUser = applicationContext.getBean("constructInjectionParamUser", User.class);
//        System.out.println(constructInjectionParamUser + "生命周期：使用对象");
        applicationContext.close();
    }
}
