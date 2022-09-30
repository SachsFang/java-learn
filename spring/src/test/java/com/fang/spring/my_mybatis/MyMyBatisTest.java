package com.fang.spring.my_mybatis;

/**
 * @author shaobin
 * @date 2022/9/13 10:54
 */
public class MyMyBatisTest {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession();
        MyUserMapper myUserMapperProxy = mySqlSession.getMapper(MyUserMapper.class);
        int result = myUserMapperProxy.insertUser("fang", 22);
        if (result > 0) {
            System.out.println("insert success");
        } else {
            System.out.println("insert fail");
        }
    }
}
