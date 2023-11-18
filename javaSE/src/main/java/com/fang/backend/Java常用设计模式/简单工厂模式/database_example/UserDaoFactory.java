package com.fang.backend.Java常用设计模式.简单工厂模式.database_example;

import java.lang.reflect.InvocationTargetException;

/**
 * @author shaobin
 * @date 2022/4/16 22:28
 */
public class UserDaoFactory {
    public UserDao getUserDao(String datasource) {
        UserDao userDao;
//        switch (datasource) {
//            case "oracle":
//                userDao = new OracleUserDaoImpl();
//                break;
//            case "mysql":
//                userDao = new MySqlUserDaoImpl();
//                break;
//            default:
//                userDao = null;
//                break;
//        }
        // 与反射结合可去除switch
        try {
            Object classInstance = Class.forName("后端.Java常用设计模式.简单工厂模式.database_example." + datasource.substring(0, 1).toUpperCase() + datasource.substring(1, datasource.length()) + "UserDaoImpl").getConstructor().newInstance();
            userDao = (UserDao) classInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userDao;
    }
}
