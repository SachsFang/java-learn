package com.fang.springboot.common.config;

import com.fang.springboot.user.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author shaobin
 * @date 2023/3/9 17:11
 */
@Configuration
public class UserServiceConfig {

    @ConditionalOnMissingBean //@ConditionalOnMissingBean，它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个。如果不加@ConditionalOnMissingBean，当你注册多个相同的bean时，会出现异常，以此来告诉开发人员
    @Bean
    public List<UserService> getAllUserService(List<UserService> userServiceList) {
        System.out.println(userServiceList);
        return userServiceList;
    }
}
