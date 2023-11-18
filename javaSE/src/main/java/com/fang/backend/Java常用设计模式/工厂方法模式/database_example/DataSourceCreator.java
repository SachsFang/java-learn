package com.fang.backend.Java常用设计模式.工厂方法模式.database_example;

import com.fang.backend.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/16 22:46
 */
public interface DataSourceCreator {

    UserDao createUserDao();

}
