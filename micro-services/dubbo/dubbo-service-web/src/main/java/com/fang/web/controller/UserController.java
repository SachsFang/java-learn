package com.fang.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/27 11:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("getUserInfo")
    public User getUserInfo() {
        return userService.getUserInfo();
    }

    @GetMapping("getUserInfoList")
    public List<User> getUserInfoList() {
        return userService.getUserInfoList();
    }
}
