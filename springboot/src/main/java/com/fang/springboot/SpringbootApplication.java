package com.fang.springboot;

import com.fang.springboot.common.init.MyApplicationContextInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.fang.springboot.user.dao")
//@EnableScheduling
@EnableAsync
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);
        springApplication.addInitializers(new MyApplicationContextInitializer());
        // 第一个参数为启动入口的类，默认整合了tomcat容器
        springApplication.run(args);
    }

}
