package com.fang.后端.lambdaAND函数式接口;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2022/8/16 15:01
 */
public class OptionalExample {
    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity("FANg", 22);
        UserEntity userEntityResultGet = Optional.ofNullable(userEntity).orElseGet(() -> {
            System.out.println("可执行其他操作");
            return new UserEntity();
        });
        UserEntity userEntityResult = Optional.ofNullable(userEntity).orElse(new UserEntity());
        System.out.println(userEntityResultGet);

        String convertStr = Optional.ofNullable(userEntity).map(UserEntity::getUserName).map(String::toLowerCase).orElse(null);
        System.out.println(convertStr);
        // 单个值用 Optional，列表使用 stream
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(userEntity);
        List<String> userEntityStrList = userEntityList.stream().map(UserEntity::getUserName).filter(Objects::nonNull).map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(userEntityStrList);
    }
}
