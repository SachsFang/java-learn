package com.fang.springboot.common.init;

import com.fang.springboot.common.util.SpringContextManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/10/10 10:28
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        SpringContextManager.setApplicationContext(configurableApplicationContext);
    }
}
