package com.fang.springboot.common.config;

import com.fang.springboot.common.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 注册自定义的显示 ResponseResult 注解的拦截器
         */
        registry.addInterceptor(new LoginInterceptor())
                // 拦截配置
                .addPathPatterns("/**")
                // 排除配置
                .excludePathPatterns("/error", "/login**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html", "swagger-ui.html", "/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");

    }
}