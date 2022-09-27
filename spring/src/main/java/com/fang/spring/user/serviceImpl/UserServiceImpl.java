package com.fang.spring.user.serviceImpl;

import com.fang.spring.user.dao.UserDao;
import com.fang.spring.user.daoImpl.UserDaoImpl;
import com.fang.spring.user.service.UserService;

/**
 * @author shaobin
 * @date 2022/9/4 12:17
 */
public class UserServiceImpl implements UserService {

    private UserDaoImpl myUserDao;

    @Override
    public void addUser() {
        // 模拟出错
        int i = 1 / 0;
        myUserDao.addUser();
    }

    public void setMyUserDao(UserDaoImpl myUserDao) {
        this.myUserDao = myUserDao;
        System.out.println("生命周期：注入IOC容器对象");
    }

    public UserDaoImpl getMyUserDao() {
        System.out.println("赋值当前对象需要通过get方法获取到当前对象");
        return myUserDao;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "myUserDao=" + myUserDao +
                '}';
    }
}
