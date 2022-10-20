package com.fang.springboot;

import com.fang.springboot.common.config.CommonRestTemplateConfig;
import com.fang.springboot.common.util.SpringContextManager;
import com.fang.springboot.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringbootApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        log.info("输出结果：" + String.valueOf(userService.insertUser("f", 24)));
    }

}
