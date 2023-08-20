package com.fang.springboot.common.serviceimpl;

import com.fang.springboot.common.service.MultiThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author shaobin
 * @date 2023/8/18 11:36
 */
@Service
public class MultiThreadServiceImpl implements MultiThreadService {
    @Autowired
    @Qualifier("main-executorService")
    private ExecutorService mainExecutorService;

    @Autowired
    @Qualifier("worker-executorService")
    private ExecutorService workerExecutorService;

    @Override
    public <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function) {
        return asyncForEach(taskList, function, null);
    }

    @Override
    public <T, R> List<R> asyncForEach(List<T> taskList, Function<T, R> function, Integer threadPoolIndex) {
        ExecutorService calcExecutorService = getCalcExecutorService(threadPoolIndex);
        List<Future<R>> futureList = new ArrayList<>();
        taskList.forEach(task -> {
            Future<R> submitFuture = calcExecutorService.submit(() -> function.apply(task));
            futureList.add(submitFuture);
        });
        List<R> rList = new ArrayList<>();
        for (Future<R> future : futureList) {
            try {
                rList.add(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return rList;
    }

    @Override
    public <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function) {
        return asyncForEach(taskMap, function, null);
    }

    @Override
    public <K, V, R> Map<K, R> asyncForEach(Map<K, V> taskMap, BiFunction<K, V, R> function, Integer threadPoolIndex) {
        ExecutorService calcExecutorService = getCalcExecutorService(threadPoolIndex);
        Map<K, Future<R>> futureMap = new HashMap<>();
        taskMap.forEach((key, value) -> {
            Future<R> submitFuture = calcExecutorService.submit(() -> function.apply(key, value));
            futureMap.put(key, submitFuture);
        });
        Map<K, R> rMap = new HashMap<>();
        futureMap.forEach((key, future) -> {
            try {
                rMap.put(key, future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return rMap;
    }

    private ExecutorService getCalcExecutorService(Integer threadPoolIndex) {
        if (Objects.nonNull(threadPoolIndex)) {
            return threadPoolIndex % 2 == 0 ? mainExecutorService : workerExecutorService;
        }
        return ((ThreadPoolExecutor) mainExecutorService).getActiveCount() <= ((ThreadPoolExecutor) workerExecutorService).getActiveCount() ?
                mainExecutorService : workerExecutorService;
    }
}
