package com.fang.spring.my_ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shaobin
 * @date 2022/9/2 15:31
 */
public class MyBeanStorage<T> {
    Map<String, T> beanMap = new ConcurrentHashMap<>();

    public Map<String, T> getBeanMap() {
        return beanMap;
    }

}
