package 后端.Java常用设计模式.简单工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/16 22:28
 */
public class UserDaoFactory {
    public UserDao getUserDao(String datasource) {
        UserDao userDao;
        switch (datasource) {
            case "oracle":
                userDao = new OracleUserDaoImpl();
                break;
            case "mysql":
                userDao = new MySqlUserDaoImpl();
                break;
            default:
                userDao = null;
                break;
        }
        return userDao;
    }
}
