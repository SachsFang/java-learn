package com.fang.micro.impl.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author shaobin
 * @date 2023/1/31 21:17
 */
public interface UserDAO {

    @Insert("INSERT INTO `users` (`name`,`age`) VALUES (#{name},#{age});")
    int insertUser(@Param("name") String name, @Param("age") Integer age);

}
