package com.fang.后端.Java常用设计模式.抽象工厂模式.database_example;

import com.fang.后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/17 14:50
 */
public class Client {
    public static void main(String[] args) {
        // 可切换数据源
//        IDataSource dataSourceFactory = new OracleFactory();
        IDataSource dataSourceFactory = new MySqlFactory();
        MenuDao menuDao = dataSourceFactory.getMenuDao();
        menuDao.updateMenu();
        menuDao.getMenu();
        UserDao userDao = dataSourceFactory.getUserDao();
        userDao.updateUser();
        userDao.getUser();
        /* 当需要进行拓展时，比如增加project，需要增加ProjectDao、OracleProjectDaoImpl、MySqlProjectDaoImpl
        * 同时需要修改IDataSource、OracleFactory、MySqlFactory、不符合开闭原则
        * 并且在没有框架的情况下，每次调用数据库方法时，都要写IDataSource dataSourceFactory = new MySqlFactory();，如果系统中有100个地方用到了， 就要改100个地方的new xxxFactory()才行，可以用简单工厂+抽象工厂进行优化*/
    }
}
