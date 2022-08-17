package 后端.Java常用设计模式.工厂方法模式.database_example;

import 后端.Java常用设计模式.简单工厂模式.database_example.MysqlUserDaoImpl;
import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/16 22:49
 */
public class MySqlCreator implements DataSourceCreator {
    @Override
    public UserDao createUserDao() {
        return new MysqlUserDaoImpl();
    }
}
