package com.fang.backend.泛型;

import com.fang.backend.lambdaAND函数式接口.UserEntity;

import java.util.Collections;
import java.util.List;

/**
 * <T> 在 class 中声明
 * @author shaobin
 * @date 2022/9/2 14:42
 */
public class ClassStatement<T> {
    public static void main(String[] args) {
        // ClassStatement<UserEntity> 指定了该实例泛型 T 为 UserEntity
        ClassStatement<UserEntity> instance = new ClassStatement<>();
        UserEntity userEntity = new UserEntity("fang", 18);
        // 所以在该实例下只能传 UserEntity 对象
        instance.getParam(userEntity);
        instance.getParamList(Collections.singletonList(userEntity));
    }

    public T getParam(T object) {
        System.out.println(object);
        return object;
    }

    public List<T> getParamList(List<T> list) {
        System.out.println(list);
        return list;
    }
}
