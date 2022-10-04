package com.fang.springboot.user.controller;

import com.fang.springboot.user.User;
import com.fang.springboot.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shaobin
 * @date 2022/7/27 18:22
 */
@Controller
@RequestMapping("/user")
public class    UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(@Param("name") String name, @Param("age") Integer age) {
        if (userService.insertUser(name, age) == 1) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "get api success";
    }

    @GetMapping("/test/spring/transactional")
    @ResponseBody
    public String testSpringTransactional() {
        return userService.testSpringAnnotationTransactional();
    }

    @GetMapping("/test/manual/transactional")
    @ResponseBody
    public String testManualTransactional() {
        return userService.testManualTransactional();
    }

    @GetMapping("/test/myself/transactional")
    @ResponseBody
    public String testMyTransactional() {
        return userService.testMyTransactional();
    }

    /**
     * thymeleaf 传参方式1 (推荐）
     * 可以避免html的传参误报错误
     * @param modelMap
     * @return
     */
    @GetMapping("/test/thymeleaf1")
    public String testThymeleaf1(ModelMap modelMap) {
        User user = new User("fang", 0, 22);
        modelMap.put("user", user);
        return "/user/index";
    }

    /**
     * thymeleaf 传参方式2
     * @return
     */
    @GetMapping("/test/thymeleaf2")
    public String testThymeleaf2(HttpServletRequest httpServletRequest) {
        User user = new User("fang", 0, 22);
        httpServletRequest.setAttribute("user", user);
        return "/user/index";
    }




}
