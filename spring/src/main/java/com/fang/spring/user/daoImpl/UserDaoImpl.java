package com.fang.spring.user.daoImpl;

import com.fang.spring.user.dao.UserDao;

/**
 * @author shaobin
 * @date 2022/9/4 12:21
 */
public class UserDaoImpl implements UserDao {

    private String name;

    @Override
    public void addUser() {
        System.out.println("在数据库添加了用户");
    }

    public void setName(String name) {
        System.out.println("UserDaoImpl 赋值：" + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
