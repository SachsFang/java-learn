package com.fang.后端.Java常用设计模式.代理;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(String name, Integer age) {
        System.out.println("add user:" + name +  " success");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user success");
    }
}
