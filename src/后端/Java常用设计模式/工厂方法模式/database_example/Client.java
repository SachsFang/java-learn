package 后端.Java常用设计模式.工厂方法模式.database_example;

import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

import javax.sql.DataSource;

/**
 * @author shaobin
 * @date 2022/4/16 22:49
 */
public class Client {
    public static void main(String[] args) {
        // 避免了简单工厂的switch，有更好的拓展性
        DataSourceCreator dataSourceCreator = new OracleCreator();
        UserDao userDao = dataSourceCreator.createUserDao();
        userDao.getUser();
        userDao.updateUser();
    }
}
