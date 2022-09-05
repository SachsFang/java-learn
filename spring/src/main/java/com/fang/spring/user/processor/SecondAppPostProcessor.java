package com.fang.spring.user.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 后置处理器
 * @author shaobin
 * @date 2022/8/31 11:51
 */
public class SecondAppPostProcessor implements BeanPostProcessor, Ordered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第二个后置处理器调用初始化方法前操作");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第二个后置处理器调用初始化方法后操作");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public int getOrder() {
        // 设置执行顺序，值越小越优先加载
        return 1;
    }
}
