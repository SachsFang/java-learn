package com.fang.springboot.user.controller;

import com.fang.springboot.user.User;
import com.fang.springboot.user.listener.UserEventListener;
import com.fang.springboot.user.properties.UserConfig;
import com.fang.springboot.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shaobin
 * @date 2022/7/27 18:22
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    UserEventListener userEventListener;

    @Value("${sachs.name}")
    private String userConfigName;

    @Value("${sachs.age}")
    private int userConfigAge;

    @Value("${sachs.sex}")
    private String userConfigSex;

    /**
     * 使用 @Value 获取配置
     * @return
     */
    @RequestMapping("/getUserConfig1")
    @ResponseBody
    public String getUserConfig1() {
        return userConfigName + " " + userConfigAge + " " + userConfigSex;
    }

    /**
     * 使用 @ConfigurationProperties 获取配置
     * @return
     */
    @RequestMapping("/getUserConfig2")
    @ResponseBody
    public String getUserConfig2() {
        return userConfig.getName() + " " + userConfig.getAge() + " " + userConfig.getSex();
    }

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
     *
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
     *
     * @return
     */
    @GetMapping("/test/thymeleaf2")
    public String testThymeleaf2(HttpServletRequest httpServletRequest) {
        User user = new User("fang", 0, 22);
        httpServletRequest.setAttribute("user", user);
        return "/user/index";
    }


    @GetMapping("/testLog")
    @ResponseBody
    public String testLog() {
        log.info("测试日志");
        return "ok";
    }

    @GetMapping("/textAsync")
    @ResponseBody
    public String testAsync() throws InterruptedException {
        log.info("<1>插入一条用户信息");
        Thread.sleep(1000);
        log.info("<2>插入成功");
        userEventListener.sendSMS();
        log.info("<4>操作完成");
        return "ok";
    }

}
