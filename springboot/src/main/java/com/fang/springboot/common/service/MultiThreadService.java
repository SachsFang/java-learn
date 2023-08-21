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
    /**
     * 集合异步循环
     * @param taskList 循环任务列表
     * @param function function
     * @return 结果集合
     */
    <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function);

    /**
     * 集合异步循环
     * @param taskList 循环任务列表
     * @param function function
     * @return 结果集合
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     * @return 结果集合
     */
    <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function, Integer threadPoolIndex);

    /**
     * map集合异步循环
     * @param taskMap 循环任务列表
     * @param function function
     * @return map结果集合
     */
    <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function);

    /**
     * 集合异步循环
     * @param taskMap 循环任务列表
     * @param function function
     * @return map结果集合
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     * @return map结果集合
     */
    <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function, Integer threadPoolIndex);
}
