package 后端.Java常用设计模式.简单工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/16 22:27
 */
public class MySqlUserDaoImpl implements UserDao {
    @Override
    public void updateUser() {
        System.out.println("mysql update user");
    }

    @Override
    public void getUser() {
        System.out.println("mysql get user");
    }
}
