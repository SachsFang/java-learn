package com.fang.springboot.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author shaobin
 * @date 2022/10/10 10:11
 */
public class SpringContextManager {
    public static ApplicationContext applicationContext;

    public static Environment environment;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextManager.applicationContext = applicationContext;
        SpringContextManager.environment = applicationContext.getEnvironment();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnvironment() {
        return environment;
    }
}
