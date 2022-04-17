package 后端.Java常用设计模式.简单工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/16 22:33
 */
public class Client {
    public static void main(String[] args) {
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        UserDao oracleUserDaoImpl = userDaoFactory.getUserDao("oracle");
        oracleUserDaoImpl.getUser();
        oracleUserDaoImpl.updateUser();

        UserDao mysqlUserImpl = userDaoFactory.getUserDao("mysql");
        mysqlUserImpl.getUser();
        mysqlUserImpl.updateUser();
    }
}
