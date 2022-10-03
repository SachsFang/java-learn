package com.fang.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fang.springboot.user.dao")
public class SpringbootApplication {

    public static void main(String[] args) {
        // 第一个参数为启动入口的类
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
