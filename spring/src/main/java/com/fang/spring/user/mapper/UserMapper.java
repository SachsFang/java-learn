package com.fang.spring.user.mapper;

import com.fang.spring.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/9/30 11:05
 */
public interface UserMapper {
    @Select("SELECT * FROM `users`")
    List<User> queryAll();

    @Select("SELECT * FROM `users` WHERE `id` = #{id}")
    User queryById(String id);

    @Insert("INSERT INTO `users`(`id`,`name`,`age`) VALUES (NULL,#{name},#{age})")
    int insert(User user);

    @Update("UPDATE `users` SET `name`=#{name},`age`=#{age} WHERE `id`=#{id}")
    int update(User user);

    @Delete("DELETE FROM `users` WHERE `id`=#{id}")
    int deleteById(String id);
}
