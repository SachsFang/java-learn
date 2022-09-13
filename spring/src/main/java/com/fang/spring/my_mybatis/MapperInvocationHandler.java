package com.fang.spring.my_mybatis;

import com.fang.spring.common.utils.JDBCUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author shaobin
 * @date 2022/9/12 21:26
 */
public class MapperInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取目标方法的注解
        MyInsert annotation = method.getDeclaredAnnotation(MyInsert.class);
        // 获取注解sql参数
        String sql = annotation.value();
        // 执行sql语句
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public <T> T getMapperProxy(Class<T> targetClass) {
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass}, this);
    }
}
