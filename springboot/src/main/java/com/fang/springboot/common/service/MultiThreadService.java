package com.fang.springboot.common.service;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *  多线程服务
 *
 * @author shaobin
 * @date 2023/8/18 11:35
 */
public interface MultiThreadService {
    <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function);

    <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function, Integer threadPoolIndex);

    <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function);

    <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function, Integer threadPoolIndex);
}
