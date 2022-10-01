package com.fang.spring.user.service;

import com.fang.spring.user.pojo.User;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/9/4 12:14
 */
public interface UserService {
    void addUser();

    /**
     * SSM整合增删改查
     */
    List<User> queryAll();
    User queryById(String id);
    int insert(User user);
    int update(User user);
    int delete(String id);
}
