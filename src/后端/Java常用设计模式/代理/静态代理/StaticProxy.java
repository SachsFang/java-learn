package 后端.Java常用设计模式.代理.静态代理;

import 后端.Java常用设计模式.代理.MyAspect;
import 后端.Java常用设计模式.代理.UserDao;

public class StaticProxy implements UserDao {// JDK动态代理是基于接口实现的，而CGLIB动态代理则是基于继承的
    UserDao userDao;
    MyAspect myAspect;

    public StaticProxy(UserDao userDao) {
        this.userDao = userDao;
        myAspect=new MyAspect();
    }

    @Override
    public void addUser(String name, Integer age) {
        myAspect.checkPermissions();
        userDao.addUser(name, age);
        myAspect.log();
    }

    @Override
    public void deleteUser() {
        myAspect.checkPermissions();
        userDao.deleteUser();
        myAspect.log();
    }
}
