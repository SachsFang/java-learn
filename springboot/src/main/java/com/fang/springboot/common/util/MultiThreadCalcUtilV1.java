package com.fang.springboot.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fang.springboot.common.function.DateBiFuntion;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.CollUtil.emptyIfNull;

/**
 * @author shaobin
 * @date 2023/11/1 14:14
 */
public class MultiThreadCalcUtilV1 {
    private static final ExecutorService calcFirstWorkerExecutorService = getDefaultCalcThreadPoolExecutor();

    private static final ExecutorService calcSecondWorkerExecutorService = getDefaultCalcThreadPoolExecutor();

    private static final ExecutorService calcThirdExecutorService = getDefaultCalcThreadPoolExecutor();

    private static ExecutorService getDefaultCalcThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 集合异步循环
     * 无返回值，主线程不等待此循环异步代码
     * @param taskList 循环任务列表
     * @param consumer consumer
     */
    public static <T> void asyncForEach(List<T> taskList, Consumer<T> consumer) {
        asyncForEach(taskList, consumer, null);
    }

    /**
     * 集合异步循环
     * @param taskList 循环任务列表
     * @param function function
     * @return 结果集合
     */
    public static <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function) {
        return asyncForEach(taskList, function, null);
    }

    /**
     * 集合异步循环
     * 无返回值，主线程不等待此循环异步代码
     * @param taskList 循环任务列表
     * @param consumer consumer
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     */
    public static <T> void asyncForEach(List<T> taskList, Consumer<T> consumer, Integer threadPoolIndex) {
        asyncForEach(taskList, consumerToFunction(consumer), threadPoolIndex);
    }

    /**
     * 集合异步循环
     * @param taskList 循环任务列表
     * @param function function
     * @return 结果集合
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     * @return 结果集合
     */
    public static <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function, Integer threadPoolIndex) {
        ExecutorService calcExecutorService = getCalcExecutorService(threadPoolIndex);
        List<Future<R>> futureList = new ArrayList<>();
        emptyIfNull(taskList).forEach(task -> {
            Future<R> submitFuture = calcExecutorService.submit(() -> function.apply(task));
            futureList.add(submitFuture);
        });
        List<R> rList = new ArrayList<>();
        for (Future<R> future : futureList) {
            try {
                R r = future.get();
                if (Objects.nonNull(r)) {
                    rList.add(r);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
//        return emptyIfNull(taskList).stream().map(function::apply).collect(Collectors.toList());
        return rList;
    }

    /**
     * map集合异步循环
     * 无返回值，主线程不等待此循环异步代码
     * @param taskMap 循环任务列表
     * @param consumer consumer
     * @return map结果集合
     */
    public static <K, V> void asyncForEach(Map<K, V> taskMap, BiConsumer<K, V> consumer) {
        asyncForEach(taskMap, consumer, null);
    }

    /**
     * map集合异步循环
     * @param taskMap 循环任务列表
     * @param function function
     * @return map结果集合
     */
    public static <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function) {
        return asyncForEach(taskMap, function, null);
    }

    /**
     * map集合异步循环
     * 无返回值，主线程不等待此循环异步代码
     * @param taskMap 循环任务列表
     * @param consumer consumer
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     */
    public static <K, V> void asyncForEach(Map<K, V> taskMap, BiConsumer<K, V> consumer, Integer threadPoolIndex) {
        asyncForEach(taskMap, consumerToFunction(consumer), threadPoolIndex);
    }

    /**
     * 集合异步循环
     * @param taskMap 循环任务列表
     * @param function function
     * @return map结果集合
     * @param threadPoolIndex 线程池索引 解决父子任务在同一线程池引发的死锁问题
     * @return map结果集合
     */
    public static <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function, Integer threadPoolIndex) {
        ExecutorService calcExecutorService = getCalcExecutorService(threadPoolIndex);
        Map<K, Future<R>> futureMap = new HashMap<>();
        taskMap.forEach((key, value) -> {
            Future<R> submitFuture = calcExecutorService.submit(() -> function.apply(key, value));
            futureMap.put(key, submitFuture);
        });
        Map<K, R> rMap = new HashMap<>();
        futureMap.forEach((key, future) -> {
            try {
                R r = future.get();
                if (Objects.nonNull(r)) {
                    rMap.put(key, future.get());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
//        taskMap.forEach((key, value) -> {
//            rMap.put(key, functionquerykey, value));
//        });
        return rMap;
    }

    /**
     * 多线程日期分段查询服务
     * 请求dubbo服务时通过该方法包装，即可实现日期分段请求
     * ps: 解决dubbo服务数据量过大超时问题
     * @param startDate
     * @param endDate
     * @param biFunction
     * @return
     * @param <R>
     */
    public static <R> List<R> queryServiceSplitDate(Date startDate, Date endDate, DateBiFuntion<R> biFunction) {
        List<Date> scopeDate = DateUtil.rangeToList(startDate, endDate, DateField.DAY_OF_MONTH).stream().map(DateTime::toJdkDate).collect(Collectors.toList());
        if (scopeDate.size() <= 1) {
            return biFunction.apply(startDate, endDate);
        }
        int availableProcessors = Runtime.getRuntime().availableProcessors() / 2;
        int cycles = Math.min(scopeDate.size(), availableProcessors);
        int incr = scopeDate.size() > availableProcessors ? Math.round(scopeDate.size() / (float) availableProcessors) : 1;
        Map<Date, Date> cyclesMap = new HashMap<>();
        if (incr > 1) {
            int startIndex = 0;
            int endIndex = 0;
            for (int i = 0; i < cycles; i++) {
                if (endIndex >= scopeDate.size()) {
                    break;
                }
                startIndex = i == 0 ? 0 : endIndex + 1;
                endIndex = i == cycles - 1 ? scopeDate.size() - 1 : startIndex + incr - 1;
                cyclesMap.put(scopeDate.get(startIndex), scopeDate.get(endIndex));
            }
        } else  {
            scopeDate.forEach(date -> {
                cyclesMap.put(date, date);
            });
        }
        return asyncForEach(cyclesMap, biFunction::apply, 3).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 多线程分页查询数据库
     * in超过数据库30%的数据量无法命中索引，可同通过多线程分页分批查询解决，首先获取到数据库查询总量 n 和 record_id 的查询总量 x，通过 x/n>0.3 则判断需要多线程查询，cycles=x/n/0.3 计算出循环次数，每个循环使用一个线程， 计算出增量 incr=x/cycles，便可得知每个线程的查询量 incr，那么每个线程查询的总数会小于数据总量的30%，便可命中索引，大幅提高性能
     * @param startDate
     * @param endDate
     * @param biFunction
     * @return
     * @param <R>
     */
    /*public static <T, R> List<R> queryDBSplitPage(AbstractJpaWrapper queryWrapper, Function<T, R> function, int count) {
        int availableProcessors = Runtime.getRuntime().availableProcessors() / 2;
        if (count > availableProcessors * 1000) {
            function.apply(queryWrapper);
        }
        int incr = count > availableProcessors * 1000 ? Math.round(count / (float) availableProcessors) : 1;
        Map<Date, Date> cyclesMap = new HashMap<>();
        if (incr > 1) {
            int startIndex = 0;
            int endIndex = 0;
            for (int i = 0; i < cycles; i++) {
                if (endIndex >= scopeDate.size()) {
                    break;
                }
                startIndex = i == 0 ? 0 : endIndex + 1;
                endIndex = i == cycles - 1 ? scopeDate.size() - 1 : startIndex + incr - 1;
                cyclesMap.put(scopeDate.get(startIndex), scopeDate.get(endIndex));
            }
        queryWrapper.last("LIMIT 1");
        return null;
    }*/

    private static ExecutorService getCalcExecutorService() {
        return getCalcExecutorService(null);
    }

    private static ExecutorService getCalcExecutorService(Integer threadPoolIndex) {
        if (Objects.nonNull(threadPoolIndex)) {
            return threadPoolIndex % 3 == 0 ? calcFirstWorkerExecutorService : threadPoolIndex % 3 == 1 ? calcSecondWorkerExecutorService : calcThirdExecutorService;
        }
        return ((ThreadPoolExecutor) calcFirstWorkerExecutorService).getActiveCount() <= ((ThreadPoolExecutor) calcSecondWorkerExecutorService).getActiveCount() ?
                calcFirstWorkerExecutorService : calcSecondWorkerExecutorService;
    }

    private static <T, R> Function<T, R> consumerToFunction(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return (R) null;
        };
    }

    private static <K, V, R> BiFunction<K, V, R> consumerToFunction(BiConsumer<K, V> consumer) {
        return (k, v) -> {
            consumer.accept(k, v);
            return (R) null;
        };
    }
}
