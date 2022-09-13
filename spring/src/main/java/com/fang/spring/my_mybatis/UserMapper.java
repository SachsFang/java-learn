package com.fang.spring.my_mybatis;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/9/12 21:41
 */
@Component
public interface UserMapper {

    @MyInsert("INSERT INTO `users` (`name`,`age`) VALUES ('mybatisTest','22');")
    int insertUser(@Param("name") String name, @Param("age") Integer age);//todo: 传参需要 MapperInvocationHandler.invoke 对参数进行处理

}
