package com.fang.backend.Java常用设计模式.简单工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/16 22:24
 */
public class OracleUserDaoImpl implements UserDao {

    @Override
    public void updateUser() {
        System.out.println("oracle update user");
    }

    @Override
    public void getUser() {
        System.out.println("oracle get user");
    }
}
