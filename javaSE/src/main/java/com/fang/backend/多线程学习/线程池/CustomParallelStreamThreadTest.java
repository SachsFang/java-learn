package com.fang.backend.多线程学习.线程池;

import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilV2;
import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilV3;
import liquibase.pro.packaged.T;
import liquibase.pro.packaged.V;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2024/4/9 15:58
 */
public class CustomParallelStreamThreadTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        CustomParallelStreamThreadTest test = new CustomParallelStreamThreadTest();
        /** 以下测试 lamuda的 parallel stream 是否帮助线程池进行计算
//        test.nativeParallel(1000, list);
//        test.customParallel(1000, list);

        /** 以下测试main线程是否帮助线程池进行计算 **/
//        asyncForEachNotMainThread(list);
        test.customParallelWithMain(list);

    }

    private static void asyncForEachNotMainThread(List<Integer> list) {
        long startTime = System.currentTimeMillis();
        List<Integer> integerList = MultiThreadCalcUtilV2.asyncForEach(list, item -> {
            // do something
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return item;
        });
        System.out.println(integerList);
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private void nativeParallel(int executeTime, List<Integer> list) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        executeTask(executeTime, list);
    }

    private void customParallel(int executeTime, List<Integer> list) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Future<List<Integer>> future = forkJoinPool.submit(() -> {
            return executeTask(executeTime, list);
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void customParallelWithMain(List<Integer> list) {
        /** 以下测试MultiThreadCalcUtil V3版本多个请求完成的任务时间和线程池中使用isQuiescent()产生的线程安全问题 **/
        asyncForEachWithMainThread(list);
        new Thread(() -> {
            List<Integer> cList = new ArrayList<>();
            for (int i = 10; i < 20; i++) {
                cList.add(i);
            }
            asyncForEachWithMainThread(cList);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> cList = new ArrayList<>();
            for (int i = 21; i < 23; i++) {
                cList.add(i);
            }
            asyncForEachWithMainThread(cList);
        }).start();
    }

    private static void asyncForEachWithMainThread(List<Integer> list) {
        long startTime = System.currentTimeMillis();
        List<Integer> integerList = MultiThreadCalcUtilV3.asyncForEach(list, item -> {
            // do something
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return item;
        });
        System.out.println(integerList);
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    static class Task extends RecursiveTask<V> {

        private T t;

        private Function<T, V> function;

        public Task(T t, Function<T, V> function) {
            this.t = t;
            this.function = function;
        }

        @Override
        protected V compute() {
            return function.apply(t);
        }

    }

    private List<Integer> executeTask(int executeTime, List<Integer> list) {
        return list.parallelStream().map(ele -> {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return ele;
        }).collect(Collectors.toList());
    }
}
