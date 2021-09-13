package 后端.Java常用设计模式.代理.JDK动态代理;

import 后端.Java常用设计模式.代理.UserDao;
import 后端.Java常用设计模式.代理.UserDaoImpl;

public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        JDKProxy jdkProxy = new JDKProxy();
        UserDao proxy = (UserDao) jdkProxy.createProxy(userDao);

        proxy.addUser();
        proxy.deleteUser();
    }
}
