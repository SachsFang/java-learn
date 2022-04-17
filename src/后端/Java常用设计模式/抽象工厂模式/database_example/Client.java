package 后端.Java常用设计模式.抽象工厂模式.database_example;

import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

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
    }
}
