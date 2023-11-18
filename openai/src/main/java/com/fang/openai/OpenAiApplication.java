package com.fang.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2023/4/24 15:00
 */
@SpringBootApplication
public class OpenAiApplication {
    public static void main(String[] args) {
        // 本项目需要连接到外网，首先要配置好vpm环境，再在vmOptions中添加-DproxyHost=127.0.0.1 -DproxyPort=1080
        SpringApplication springApplication = new SpringApplication(OpenAiApplication.class);
        springApplication.run(args);
    }
}
