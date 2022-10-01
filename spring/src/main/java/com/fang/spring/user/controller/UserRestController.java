package com.fang.spring.user.controller;

import com.fang.spring.common.builder.BaseRespBuilder;
import com.fang.spring.common.pojo.BaseResp;
import com.fang.spring.user.pojo.User;
import com.fang.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shaobin
 * @date 2022/9/23 15:53
 */
@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public String testServlet() {
        return "ok";
    }

    @GetMapping(value = "/param1")
    public String param1(String name, @RequestParam(value = "age", required = false) Integer age1) {
        return name + age1;
    }

    @GetMapping("/param2")
    public String param2(User user) {
        return user.toString();
    }

    @GetMapping("/param3")
    public String param3(String[] values) {
        return Arrays.toString(values);
    }

    @GetMapping("/param4")
    public String param4(@RequestBody List<User> users) {
        return users.toString();
    }

    @GetMapping("/param5")
    public String param5(@RequestBody Map<String, Object> map) {
        return map.toString();
    }

    @GetMapping("/param6")
    public String param6(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String name = httpServletRequest.getParameter("name");
        int age = Integer.parseInt(httpServletRequest.getParameter("age"));
        return name + age;
    }

    @GetMapping("/param7")
    public String param7() {
        // 从ThreadLocal获取HttpServletRequest  springmvc 底层基于Servlet 提前缓存好了HttpServletRequest
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = httpServletRequest.getParameter("name");
        int age = Integer.parseInt(httpServletRequest.getParameter("age"));
        return name + age;
    }

    /**
     * Restful风格的增删改查
     */
    @GetMapping("/all")
    public BaseResp<List<User>> queryAllUser() {
        return BaseRespBuilder.success().setData(userService.queryAll()).build();
    }

    @GetMapping("/{id}")
    public BaseResp<User> queryUserById(@PathVariable("id") String id) {
        return BaseRespBuilder.success().setData(userService.queryById(id)).build();
    }

    @PostMapping
    public BaseResp insertUser(User user) {
        if (userService.insert(user) > 0) {
            return BaseRespBuilder.success().build();
        } else {
            return BaseRespBuilder.fail().build();
        }
    }

    @PutMapping
    public BaseResp updateUser(User user) {
        if (userService.update(user) > 0) {
            return BaseRespBuilder.success().build();
        } else {
            return BaseRespBuilder.fail().build();
        }
    }

    @DeleteMapping("/{id}")
    public BaseResp deleteUser(@PathVariable("id") String id) {
        if (userService.delete(id) > 0) {
            return BaseRespBuilder.success().build();
        } else {
            return BaseRespBuilder.fail().build();
        }
    }

    @Transactional
    @PostMapping("/transactional")
    public BaseResp<Void> testTransactional(User user) {
        userService.insert(user);
        int i = 1 / 0;
        userService.insert(user);
        return BaseRespBuilder.success().build();
    }
}
