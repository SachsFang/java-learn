package com.fang.springboot.user.dao;

import com.fang.springboot.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shaobin
 * @date 2022/8/5 19:54
 */
@Repository
@Transactional
public interface UserDAO extends JpaRepository<User, String> {

    @Modifying
    @Query(value = "INSERT INTO users (name,age) VALUES (:name,:age)",
            nativeQuery = true)
    int insertUser(@Param("name") String name, @Param("age") Integer age);

}