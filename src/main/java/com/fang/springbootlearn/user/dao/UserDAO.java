package com.fang.springbootlearn.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author shaobin
 * @date 2022/8/5 19:54
 */
public interface UserDAO {

    @Insert("INSERT INTO `users` (`name`,`age`) VALUES (#{name},#{age});")
    int insertUser(@Param("name") String name, @Param("age") Integer age);

}
