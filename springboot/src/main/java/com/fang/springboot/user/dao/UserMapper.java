package com.fang.springboot.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.springboot.user.enums.SexEnum;
import com.fang.springboot.user.pojo.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author shaobin
 * @date 2023/11/26 12:00
 */
public interface UserMapper extends BaseMapper<UserPO> {
    @Insert("INSERT INTO users (name,age,sex) VALUES (#{name},#{age},#{sex});")
    int insertUser(@Param("name") String name, @Param("age") Integer age, @Param("sex") SexEnum sex);

}
