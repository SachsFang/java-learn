package com.fang.spring.my_mybatis;

/**
 * @author shaobin
 * @date 2022/9/12 21:24
 */
public class MySqlSession {
    public <T> T getMapper(Class<T> targetClass) {
        MapperInvocationHandler mapperInvocationHandler = new MapperInvocationHandler();
        T mapperProxy = mapperInvocationHandler.getMapperProxy(targetClass);
        return mapperProxy;
    }
}
