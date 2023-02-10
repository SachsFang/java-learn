package com.fang.micro.impl.user.dao;

import com.fang.micro.api.user.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author shaobin
 * @date 2023/1/31 21:17
 */
public interface UserDAO {

    @Select("SELECT * FROM `users`;")
    List<User> getUserList();

    @Insert("INSERT INTO `users` (`name`,`age`) VALUES (#{name},#{age});")
    int insertUser(@Param("name") String name, @Param("age") Integer age);

}
