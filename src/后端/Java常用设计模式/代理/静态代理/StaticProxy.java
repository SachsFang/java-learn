package 后端.Java常用设计模式.代理.静态代理;

import 后端.Java常用设计模式.代理.MyAspect;
import 后端.Java常用设计模式.代理.UserDao;

public class StaticProxy implements UserDao {
    UserDao userDao;
    MyAspect myAspect;

    public StaticProxy(UserDao userDao) {
        this.userDao = userDao;
        myAspect=new MyAspect();
    }

    @Override
    public void addUser() {
        myAspect.checkPermissions();
        userDao.addUser();
        myAspect.log();
    }

    @Override
    public void deleteUser() {
        myAspect.checkPermissions();
        userDao.deleteUser();
        myAspect.log();
    }
}
