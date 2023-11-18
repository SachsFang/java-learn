package com.fang.springboot.common.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2023/2/24 14:33
 */
public class ObjectUtil {

    public static <S, T> List<T> copyToList(List<S> sources, Class<T> targetClazz) {
        List<T> targets = new ArrayList<>();
        for (S source : sources) {
            targets.add(copy(source, targetClazz));
        }
        return targets;
    }

    public static <S, T> T copy(S source, Class<T> targetClazz) {
        if (source == null) {
            return null;
        }
        String jsonString = JSON.toJSONString(source);
        return JSON.parseObject(jsonString, targetClazz);
    }
}
