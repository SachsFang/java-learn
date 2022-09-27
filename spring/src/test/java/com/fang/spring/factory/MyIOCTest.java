package com.fang.spring.factory;

import com.fang.spring.my_ioc.MyBeanFactory;
import com.fang.spring.user.pojo.User;
import org.dom4j.DocumentException;

/**
 * @author shaobin
 * @date 2022/9/2 12:47
 */
public class MyIOCTest {
    public static void main(String[] args) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 测试自己手写的简单单例IOC容器
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        User user = (User) myBeanFactory.getBean("haveParamUser");
        user.setName("fang");
        System.out.println(user);
    }
}
