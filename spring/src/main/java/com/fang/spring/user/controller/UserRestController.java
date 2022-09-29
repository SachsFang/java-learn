package com.fang.spring.user.controller;

import com.fang.spring.user.pojo.User;
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
        Integer age = Integer.valueOf(httpServletRequest.getParameter("age"));
        return name + age;
    }

    @GetMapping("/param7")
    public String param7() {
        // 从ThreadLocal获取HttpServletRequest  springmvc 底层基于Servlet 提前缓存好了HttpServletRequest
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = httpServletRequest.getParameter("name");
        Integer age = Integer.valueOf(httpServletRequest.getParameter("age"));
        return name + age;
    }

}
