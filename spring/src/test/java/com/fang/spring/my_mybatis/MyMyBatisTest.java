package com.fang.spring.my_mybatis;

import com.fang.spring.my_mybatis.MySqlSession;
import com.fang.spring.my_mybatis.UserMapper;

/**
 * @author shaobin
 * @date 2022/9/13 10:54
 */
public class MyMyBatisTest {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession();
        UserMapper userMapperProxy = mySqlSession.getMapper(UserMapper.class);
        int result = userMapperProxy.insertUser("fang", 22);
        if (result > 0) {
            System.out.println("insert success");
        } else {
            System.out.println("insert fail");
        }
    }
}
