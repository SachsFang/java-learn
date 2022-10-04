package com.fang.springboot.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sachs")
@Data
public class UserConfig {
    private String name;
    private int age;
    private String sex;
}
