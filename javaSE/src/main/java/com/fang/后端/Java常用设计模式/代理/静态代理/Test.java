package com.fang.后端.Java常用设计模式.代理.静态代理;

import com.fang.后端.Java常用设计模式.代理.UserDao;
import com.fang.后端.Java常用设计模式.代理.UserDaoImpl;

public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        StaticProxy proxy = new StaticProxy(userDao);
        proxy.addUser("fang", 22);
        proxy.deleteUser();
    }
}
