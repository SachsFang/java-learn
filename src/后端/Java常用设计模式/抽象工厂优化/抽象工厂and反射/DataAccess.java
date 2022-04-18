package 后端.Java常用设计模式.抽象工厂优化.抽象工厂and反射;

import 后端.Java常用设计模式.抽象工厂模式.database_example.IDataSource;
import 后端.Java常用设计模式.抽象工厂模式.database_example.MenuDao;
import 后端.Java常用设计模式.简单工厂模式.database_example.UserDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author shaobin
 * @date 2022/4/17 15:44
 */
public class DataAccess {
//    private static String db = "Oracle";
    private static String path = "后端.Java常用设计模式.抽象工厂模式.database_example.";

    public static UserDao getUserDao() {
        return getDataSourceFactory().getUserDao();
    }

    public static MenuDao getMenuDao() {
        return getDataSourceFactory().getMenuDao();
    }

    private static IDataSource getDataSourceFactory() {
        // 从配置文件获取变量
        Properties properties = new Properties();
        InputStream configIn = DataAccess.class.getClassLoader().getResourceAsStream("后端/Java常用设计模式/抽象工厂优化/抽象工厂and反射/properties.yml");
        IDataSource datasourceFactory = null;
        try {
            properties.load(configIn);
            Class<?> datasourceFacClass = Class.forName(path + properties.getProperty("db") + "Factory");
            datasourceFactory = (IDataSource) datasourceFacClass.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return datasourceFactory;
    }
}
