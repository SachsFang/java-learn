package com.fang.spring.spring_mvc_project_start;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * @author shaobin
 * @date 2022/9/24 11:27
 * @description  初始化前端控制器 - 注解方式启动的配置（ps：使用这种方式启动时把web.xml注释掉）
 */
public class ServletInitializer extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        // 加入类配置注解
        webApplicationContext.register(SpringMvcConfig.class);
        return webApplicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
