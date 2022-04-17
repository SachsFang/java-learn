package 后端.Java常用设计模式.抽象工厂模式.database_example;

import 后端.Java常用设计模式.简单工厂模式.database_example.MySqlUserDaoImpl;
import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/17 14:42
 */
public class MySqlFactory implements IDataSource {
    @Override
    public UserDao getUserDao() {
        return new MySqlUserDaoImpl();
    }

    @Override
    public MenuDao getMenuDao() {
        return new MySqlMenuDaoImpl();
    }
}
