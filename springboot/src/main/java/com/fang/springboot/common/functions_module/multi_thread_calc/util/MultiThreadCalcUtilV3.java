package com.fang.springboot.common.functions_module.multi_thread_calc.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fang.springboot.common.function.DateBiFuntion;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * MultiThreadCalcUtil V3版本
 *
 * @author shaobin
 * @date 2023/8/23 14:45
 * @description 此版本更新较大，主要为：
 * 1.提交任务的主线程不再阻塞等待线程池执行，也会参与到总任务的计算当中。
 * 2.通过第一点的改造，解决了请求量大造成的线程饥饿问题（线程池线程优先，而请求的线程阻塞等待，如果线程池处理不过来，
 * 会造成大量请求线程阻塞，导致线程饥饿，并且jvm垃圾收集器无法清理这些有用的阻塞线程，当老年代内存刚不住的时候最终会造成OOM问题）
 */
public class MultiThreadCalcUtilV3 {

    private static final AtomicInteger lock = new AtomicInteger();

    protected static ForkJoinPool calcWorkerExecutorPool;

    public static <T> void async(Runnable runnable) {
        ForkJoinPool calcExecutorPool = getCalcExecutorPool();
        calcExecutorPool.submit(runnable);
    }

    public static <T> void asyncForEach(Collection<T> taskList, Consumer<T> consumer) {
        asyncForEach(taskList, consumerToFunction(consumer));
    }

    public static <T, R> List<R> asyncForEach(Collection<T> list, Function<T, R> function) {
        ForkJoinPool calcExecutorPool = getCalcExecutorPool();
        // 线程池不空闲时拒绝使用多线程，减轻系统负担，因为cpu资源紧张，如果cpu资源充裕可去除此处if代码。ps：使用calcExecutorPool.isQuiescent需解决线程安全问题
//        if (!calcExecutorPool.isQuiescent() || lock.get() >= 1) {
//            return list.stream().map(function).collect(Collectors.toList());
//        }
//        lock.getAndIncrement();
        List<Task<T, R>> taskList = new ArrayList<>();
        try {
            list.forEach(item -> {
                Task<T, R> task = new Task<T, R>(item, function);
                calcExecutorPool.submit(task);
                taskList.add(task);
            });
            doTask(calcExecutorPool, taskList);
        } catch (Exception e) {
            throw e;
        } finally {
//            lock.getAndDecrement();
        }
        return taskList.stream().map(Task::getResult).collect(Collectors.toList());
    }

    public static <K, V> void asyncForEach(Map<K, V> taskMap, BiConsumer<K, V> consumer) {
        asyncForEach(taskMap, consumerToFunction(consumer));
    }

    public static <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> biFunction) {
        ForkJoinPool calcExecutorPool = getCalcExecutorPool();
        Map<K, R> rMap = new HashMap<>();
        // 线程池不空闲时拒绝使用多线程，减轻系统负担，因为cpu资源紧张，如果cpu资源充裕可去除此处if代码。
//        if (!calcExecutorPool.isQuiescent() || lock.get() >= 1) {
//            taskMap.forEach((key, value) -> rMap.put(key, biFunction.apply(key, value)));
//            return rMap;
//        }
//        lock.getAndIncrement();
        List<Task<Map.Entry<K, V>, R>> taskList = new ArrayList<>();
        try {
            new ArrayList<>(taskMap.entrySet()).forEach(kvEntry -> {
                R result = biFunction.apply(kvEntry.getKey(), kvEntry.getValue());
                Function<Map.Entry<K, V>, R> function = (kvEntry1 -> {
                    return result;
                });
                if (Objects.nonNull(kvEntry.getKey()) && Objects.nonNull(result)) {
                    Task<Map.Entry<K, V>, R> task = new Task<Map.Entry<K, V>, R>(kvEntry, function);
                    calcExecutorPool.submit(task);
                    taskList.add(task);
                }
            });
            doTask(calcExecutorPool, taskList);
        } catch (Exception e) {
            throw e;
        } finally {
//            lock.getAndDecrement();
        }
        taskList.forEach(item -> {
            rMap.put(item.getT().getKey(), item.getResult());
        });
        return rMap;
    }

    public static <K, V, R> Map<K, R> forEach(Map<K, V> taskMap, BiFunction<K, V, R> function) {
        Map<K, R> rMap = new HashMap<>();
        taskMap.forEach((key, value) -> rMap.put(key, function.apply(key, value)));
        return rMap;
    }

    public static <R> List<R> asyncShardingByDate(Date startDate, Date endDate, DateBiFuntion<R> biFunction) {
        return asyncShardingByDate(startDate, endDate, null, biFunction);
    }

    /**
     * 异步日期分片功能
     * ps: 1.异步分片查询加快整体查询速度 2.解决dubbo服务单次调用数据量过大、超时问题 3.单个数据库请求索引失效
     *
     * @param startDate
     * @param endDate
     * @param shardingCount
     * @param biFunction
     * @param <R>
     * @return
     */
    public static <R> List<R> asyncShardingByDate(Date startDate, Date endDate, Integer shardingCount, DateBiFuntion<R> biFunction) {
        List<Date> scopeDate = DateUtil.rangeToList(startDate, endDate, DateField.DAY_OF_MONTH).stream().map(DateTime::toJdkDate).collect(Collectors.toList());
        if (scopeDate.size() <= 31) {
            return biFunction.apply(startDate, endDate);
        }
        int availableProcessors = Objects.nonNull(shardingCount) ? shardingCount : getCalcExecutorPool().getParallelism();
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
                endIndex = i == cycles - 1 || i * incr + 1 == scopeDate.size() ? scopeDate.size() - 1 : startIndex + incr - 1;
                if (startIndex > scopeDate.size() - 1 ||endIndex > scopeDate.size() - 1) {
                    break;
                }
                cyclesMap.put(scopeDate.get(startIndex), scopeDate.get(endIndex));
            }
        } else {
            scopeDate.forEach(date -> {
                cyclesMap.put(date, date);
            });
        }
        return asyncForEach(cyclesMap, biFunction::apply).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static <T, R> void doTask(ForkJoinPool calcExecutorPool, List<Task<T, R>> taskList) {
        // 如果线程池有任务在执行（不空闲），则使用主线程帮忙
        int count = 0;
        while (count < taskList.size()) {
            count = 0;
            for (Task<T, R> task : taskList) {
                if (!task.isStart()) {
                    // 直接在主线程中执行任务
                    task.compute();
                }
                if (task.isComplete()) {
                    count++;
                }
            }
            if (count >= taskList.size()) {
                break;
            }
            // 可以选择在此处添加一些延迟，以避免忙等待（cpu空转）
            try {
                // 例如，等待100毫秒后再次检查
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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

    static class Task<T, R> extends RecursiveTask<R> {


        private T t;

        private R r;

        private Function<T, R> function;

        private TaskState taskState;

        public Task(T t, Function<T, R> function) {
            this.t = t;
            this.function = function;
            this.taskState = TaskState.NOT_STARTED;
        }

        @Override
        protected R compute() {
            if (!isStart()) {
                this.taskState = TaskState.RUNNING;
                try {
                    this.r = function.apply(t);
                } catch (Exception e) {
                    this.taskState = TaskState.FAILED;
                    throw e;
                }
                this.taskState = TaskState.COMPLETED;
            }
            return this.r;
        }

        public T getT() {
            return t;
        }

        private R getResult() {
            return this.r;
        }

        public TaskState getTaskState() {
            return taskState;
        }

        public boolean isStart() {
            return !Objects.equals(this.taskState, TaskState.NOT_STARTED);
        }

        public boolean isComplete() {
            return Objects.equals(this.taskState, TaskState.COMPLETED) ||
                    Objects.equals(this.taskState, TaskState.FAILED);
        }

        enum TaskState {
            NOT_STARTED, RUNNING, COMPLETED, FAILED
        }

    }
}
