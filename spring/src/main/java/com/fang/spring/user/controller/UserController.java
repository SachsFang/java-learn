package com.fang.spring.user.controller;

import com.fang.spring.user.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author shaobin
 * @date 2022/9/23 15:53
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/test")
    public String testServlet() {
        return "ok";
    }

}
