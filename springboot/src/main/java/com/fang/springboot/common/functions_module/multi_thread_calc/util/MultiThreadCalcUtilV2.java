package com.fang.springboot.common.functions_module.multi_thread_calc.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fang.springboot.common.function.DateBiFuntion;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author shaobin
 * @date 2023/8/23 14:45
 */
public class MultiThreadCalcUtilV2 {

    private static final AtomicInteger lock = new AtomicInteger();

    private static final long TIME_OUT = 120L;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private static final ForkJoinPool calcWorkerExecutorPool = getDefaultCalcThreadPoolExecutor();

    private static ForkJoinPool getDefaultCalcThreadPoolExecutor() {
        return new ForkJoinPool(1);
//        return new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
    }

    public static <T> void asyncForEach(Collection<T> taskList, Consumer<T> consumer) {
        asyncForEach(taskList, consumerToFunction(consumer));
    }

    public static <T, R> List<R> asyncForEach(Collection<T> taskList, Function<T, R> function) {
        ForkJoinPool calcExecutorPool = new ForkJoinPool(2);
//        if (calcExecutorPool.getParallelism() - calcExecutorPool.getActiveThreadCount() <= 0) {
//            return taskList.stream().map(function).collect(Collectors.toList());
//        }
        Future<List<R>> future = calcExecutorPool.submit(() -> {
            return taskList.parallelStream().map(function).collect(Collectors.toList());
        });
        List<R> rList = null;
        try {
            rList = future.get(TIME_OUT, TIME_UNIT);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
            throw new RuntimeException(e);
        }
        return rList;
    }

    public static <K, V> void asyncForEach(Map<K, V> taskMap, BiConsumer<K, V> consumer) {
        asyncForEach(taskMap, consumerToFunction(consumer));
    }

    public static <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function) {
        ForkJoinPool calcExecutorPool = getCalcExecutorPool();
//        if (calcExecutorPool.getParallelism() - calcExecutorPool.getActiveThreadCount() <= 0) {
//            Map<K, R> rMap = new HashMap<>();
//            taskMap.forEach((key, value) -> rMap.put(key, function.apply(key, value)));
//            return rMap;
//        }
        Map<K, R> rMap = new ConcurrentHashMap<>();
        Future<List<R>> future = calcExecutorPool.submit(() ->
                // ps:Set/Map.entrySet不支持并行流，使用并行会失效，先把Map中的key转成list，再用并行流能生效了
                new ArrayList<>(taskMap.entrySet()).parallelStream().map(kvEntry -> {
                    R result = function.apply(kvEntry.getKey(), kvEntry.getValue());
                    if (Objects.nonNull(kvEntry.getKey()) && Objects.nonNull(result)) {
                        rMap.put(kvEntry.getKey(), result);
                        return result;
                    }
                    return null;
                }).collect(Collectors.toList()));
        try {
            future.get(TIME_OUT, TIME_UNIT);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
            throw new RuntimeException(e);
        }
        return rMap;
    }

    /**
     * 异步日期分片功能
     * ps: 1.异步分片查询加快整体查询速度 2.解决dubbo服务单次调用数据量过大、超时问题
     *
     * @param startDate
     * @param endDate
     * @param biFunction
     * @param <R>
     * @return
     */
    public static <R> List<R> asyncShardingByDate(Date startDate, Date endDate, DateBiFuntion<R> biFunction) {
        List<Date> scopeDate = DateUtil.rangeToList(startDate, endDate, DateField.DAY_OF_MONTH).stream().map(DateTime::toJdkDate).collect(Collectors.toList());
        if (scopeDate.size() <= 1) {
            return biFunction.apply(startDate, endDate);
        }
//        int availableProcessors = Runtime.getRuntime().availableProcessors() - 1;
        int availableProcessors = 8 - 1;
        int cycles = Math.min(scopeDate.size(), availableProcessors);
        int incr = scopeDate.size() > availableProcessors ? (int) Math.ceil(scopeDate.size() / (double) availableProcessors) : 1;
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
        } else {
            scopeDate.forEach(date -> {
                cyclesMap.put(date, date);
            });
        }
        return asyncForEach(cyclesMap, biFunction::apply).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static ForkJoinPool getCalcExecutorPool() {
        return calcWorkerExecutorPool;
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

    /**
     * 多线程分页查询数据库
     * in超过数据库30%的数据量无法命中索引，可同通过多线程分页分批查询解决，首先获取到数据库查询总量 n 和 record_id 的查询总量 x，通过 x/n>0.3 则判断需要多线程查询，cycles=x/n/0.3 计算出循环次数，每个循环使用一个线程， 计算出增量 incr=x/cycles，便可得知每个线程的查询量 incr，那么每个线程查询的总数会小于数据总量的30%，便可命中索引，大幅提高性能
     *
     * @param <R>
     * @return
     */
    /*public static <T, R> List<R> queryDBSplitPage(AbstractJpaWrapper queryWrapper, Function<T, R> function, int count) {
        int availableProcessors = AVAILABLE_PROCESSORS / 2;
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
}
