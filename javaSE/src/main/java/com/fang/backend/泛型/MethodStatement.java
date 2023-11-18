package com.fang.backend.泛型;

import com.fang.backend.lambdaAND函数式接口.UserEntity;

import java.util.Collections;
import java.util.List;

/**
 * <T> 在 方法 中声明
 * @author shaobin
 * @date 2022/9/2 14:43
 */
public class MethodStatement {
    public static void main(String[] args) {
        MethodStatement instance = new MethodStatement();
        UserEntity userEntity = new UserEntity("fang", 19);
        // 该实例的方法中声明了泛型，可传任意类型的对象
        instance.getParam(userEntity);
        instance.getParamList(Collections.singletonList(userEntity));
    }

    public <T> T getParam(T object) {
        System.out.println(object);
        return object;
    }

    public <T> List<T> getParamList(List<T> list) {
        System.out.println(list);
        return list;
    }

}
