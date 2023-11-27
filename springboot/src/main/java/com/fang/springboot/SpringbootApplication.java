package com.fang.springboot;

import com.fang.springboot.common.init.MyApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
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
