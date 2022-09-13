package com.fang.spring.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author shaobin
 * @date 2022/9/13 10:23
 */
public class JDBCUtil {
    private static String driverClassName;
    private static String url;
    private static String userName;/**/
    private static String password;/**/

    static {
        try {
            /*读取config.properties  IO 路径 相对路径*/
            InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("application.yml");
            /*赋值给我们声明好的变量*/
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            url = properties.getProperty("urlTest");
            driverClassName = properties.getProperty("driver-class-name");
            userName = properties.getProperty("username");
            password = properties.getProperty("password");
            /*注册驱动类*/
            Class.forName(driverClassName);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Statement statement, Connection connection) {
        closeConnection(null, statement, connection);
    }

    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
