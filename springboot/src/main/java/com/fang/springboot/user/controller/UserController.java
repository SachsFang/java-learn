package com.fang.springboot.user.controller;

import com.fang.springboot.common.pojo.BaseResp;
import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilV2;
import com.fang.springboot.common.util.SpringContextManager;
import com.fang.springboot.user.listener.UserEventListener;
import com.fang.springboot.user.pojo.User;
import com.fang.springboot.user.pojo.UserEvent;
import com.fang.springboot.user.properties.UserConfig;
import com.fang.springboot.user.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    public String jpaInsert(String name, Integer age, Integer sex) {
        if (userService.jpaInsertUser(name, age, sex) == 1) {
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
        User user = new User("a", "fang", 0, 22);
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
        User user = new User("b", "fang", 0, 22);
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
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
//        System.out.println("---测试有返回值的异步循环---");
//        long startTime = System.currentTimeMillis();
//        List<User> resultList = multiThreadService.asyncForEach(list, item -> {
//            User user = new User();
//            try {
//                Thread.sleep((long) (Math.random() * 100));
//                user.setName("我是方");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return user;
//        });
//        System.out.println(resultList.size());
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime - startTime);

        System.out.println("---测试无返回值的异步循环---");
        // 注意不能使用new ArrayList<>()，会有线程安全问题，导致最终结果不一致
        List<User> testNullList = new Vector<>();
        long startTime2 = System.currentTimeMillis();
        MultiThreadCalcUtilV2.asyncForEach(list, item -> {
            try {
                User user = new User();
                double random = Math.random();
                Thread.sleep((long) (random * 100));
                user.setName(String.valueOf(random));
                testNullList.add(user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(testNullList.stream().filter(item -> Objects.isNull(item.getName())).count());
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);


        return "ok";
    }

    private static ExecutorService executor = Executors.newSingleThreadExecutor(); // 创建一个单线程的线程池

    @GetMapping("timeout")
    @SneakyThrows
    @ResponseBody
    public String timeout() {
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(71000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("还在计算");
            return "thread ok";
        });
        future.get();
        return "ok";
    }

}
