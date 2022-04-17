package 后端.Java常用设计模式.抽象工厂模式.database_example;

import 后端.Java常用设计模式.简单工厂模式.database_example.OracleUserDaoImpl;
import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/17 14:42
 */
public class OracleFactory implements IDataSource {
    @Override
    public UserDao getUserDao() {
        return new OracleUserDaoImpl();
    }

    @Override
    public MenuDao getMenuDao() {
        return new OracleMenuDaoImpl();
    }
}
