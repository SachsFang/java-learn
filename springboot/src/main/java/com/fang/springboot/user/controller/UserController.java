package com.fang.springboot.user.controller;

import com.fang.springboot.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2022/7/27 18:22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    public String insert(@Param("name") String name, @Param("age") Integer age) {
        if (userService.insertUser(name, age) == 1) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/test")
    public String test() {
        return "get api success";
    }

    @GetMapping("/test/spring/transactional")
    public String testSpringTransactional() {
        return userService.testSpringAnnotationTransactional();
    }

    @GetMapping("/test/manual/transactional")
    public String testManualTransactional() {
        return userService.testManualTransactional();
    }

    @GetMapping("/test/myself/transactional")
    public String testMyTransactional() {
        return userService.testMyTransactional();
    }
}
