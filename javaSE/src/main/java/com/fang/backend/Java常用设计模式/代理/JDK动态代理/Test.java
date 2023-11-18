package com.fang.backend.Java常用设计模式.代理.JDK动态代理;

import com.fang.backend.Java常用设计模式.代理.UserDao;
import com.fang.backend.Java常用设计模式.代理.UserDaoImpl;

public class Test {
    public static void main(String[] args) {
        // 将jdk动态代理生成好的 class 文件存放到本地（生成后放在 /com/sun/proxy 以方便研究代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        JDKProxyInvocationHandler jdkProxyInvocationHandler = new JDKProxyInvocationHandler();
        UserDao proxy = (UserDao) jdkProxyInvocationHandler.createProxy(new UserDaoImpl());

        proxy.addUser("fang", 22);
        proxy.deleteUser();
    }
}
