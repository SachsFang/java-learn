package 后端.Java常用设计模式.抽象工厂模式.database_example;


import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

/**
 * @author shaobin
 * @date 2022/4/17 14:38
 */
public interface IDataSource {

    UserDao getUserDao();

    MenuDao getMenuDao();
}
