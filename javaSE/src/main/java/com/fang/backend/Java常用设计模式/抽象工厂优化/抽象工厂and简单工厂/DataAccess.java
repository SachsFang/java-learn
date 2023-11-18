package com.fang.backend.Java常用设计模式.抽象工厂优化.抽象工厂and简单工厂;

import com.fang.backend.Java常用设计模式.抽象工厂模式.database_example.MenuDao;
import com.fang.backend.Java常用设计模式.抽象工厂模式.database_example.OracleMenuDaoImpl;
import com.fang.backend.Java常用设计模式.简单工厂模式.database_example.MysqlUserDaoImpl;
import com.fang.backend.Java常用设计模式.简单工厂模式.database_example.OracleUserDaoImpl;
import com.fang.backend.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/17 15:18
 */
public class DataAccess {
    private static String db = "oracle";

    public static UserDao getUserDao() {
        switch (db) {
            case "oracle":
                return new OracleUserDaoImpl();
            case "mysql":
                return new MysqlUserDaoImpl();
            default:
                return null;
        }
    }

    public static MenuDao getMenuDao() {
        switch (db) {
            case "oracle":
                return new OracleMenuDaoImpl();
            case "mysql":
                return new OracleMenuDaoImpl();
            default:
                return null;
        }
    }
}
