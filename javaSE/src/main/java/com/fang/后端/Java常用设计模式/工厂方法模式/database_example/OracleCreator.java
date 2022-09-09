package com.fang.后端.Java常用设计模式.工厂方法模式.database_example;

import com.fang.后端.Java常用设计模式.简单工厂模式.database_example.OracleUserDaoImpl;
import com.fang.后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/16 22:48
 */
public class OracleCreator implements DataSourceCreator {
    @Override
    public UserDao createUserDao() {
        return new OracleUserDaoImpl();
    }
}
