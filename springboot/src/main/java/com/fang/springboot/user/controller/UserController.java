package com.fang.springboot.user.controller;

import com.fang.springboot.common.pojo.BaseResp;
import com.fang.springboot.common.service.MultiThreadService;
import com.fang.springboot.common.util.SpringContextManager;
import com.fang.springboot.user.listener.UserEventListener;
import com.fang.springboot.user.pojo.User;
import com.fang.springboot.user.pojo.UserEvent;
import com.fang.springboot.user.properties.UserConfig;
import com.fang.springboot.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/7/27 18:22
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private UserEventListener userEventListener;

    @Autowired
    private MultiThreadService multiThreadService;

    /**
     * 获取Spring上下文环境也可以使用注入的形式
     */
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sachs.name}")
    private String userConfigName;

    @Value("${sachs.age}")
    private int userConfigAge;

    @Value("${sachs.sex}")
    private String userConfigSex;

    private String TEST_URL = "https://adssx-test-gzdevops.tsintergy.com/usercenter/web/pf/login/info/publicKey";

    /**
     * 使用 @Value 获取配置
     *
     * @return
     */
    @RequestMapping("/getUserConfig1")
    @ResponseBody
    public String getUserConfig1() {
        return userConfigName + " " + userConfigAge + " " + userConfigSex;
    }

    /**
     * 使用 @ConfigurationProperties 获取配置
     *
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
    public String testAsync() throws InterruptedException {
        log.info("<1>插入一条用户信息");
        Thread.sleep(1000);
        log.info("<2>插入成功");
        userEventListener.sendSMS();
        log.info("<4>操作完成");
        return "ok";
    }

    @GetMapping("/textListener")
    @ResponseBody
    public String testListener(UserEvent userEvent) throws InterruptedException {
        log.info("<1>插入一条用户信息");
        Thread.sleep(1000);
        log.info("<2>插入成功");
//        applicationContext.publishEvent(new UserEvent("fang", "发送了事件"));
        SpringContextManager.getApplicationContext().publishEvent(new UserEvent("fang", "发送了事件"));
        log.info("<4>操作完成");
        return "ok";
    }

    @GetMapping("/testError")
    @ResponseBody
    public String testError() {
        int i = 1 / 0;
        return "ok";
    }

    @GetMapping("/testRestTemplate")
    @ResponseBody
    public String testRestTemplate() {
        ResponseEntity<BaseResp> response = restTemplate.getForEntity(TEST_URL, BaseResp.class);
        BaseResp baseResp = response.getBody();
        assert baseResp != null;
        log.info(baseResp.getData().toString());
        return baseResp.getData().toString();
    }

    @GetMapping("/testMultiThreadService")
    @ResponseBody
    public String testMultiThreadService() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        long startTime = System.currentTimeMillis();
        List<Integer> resultList = multiThreadService.asyncForEach(list, item -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return item;
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return resultList.toString();
    }

}
