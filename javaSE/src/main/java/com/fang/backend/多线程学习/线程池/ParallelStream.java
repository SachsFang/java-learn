package com.fang.backend.多线程学习.线程池;

import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilV2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2023/10/19 16:11
 */
public class ParallelStream {
    public static void main(String[] args) {
        ParallelStream main = new ParallelStream();
        main.testExecutorService(2, 1000);
//        main.testSetThreadParallelStream(4, 100);
//        main.testCustomPoolParallelStream(4, 100);
//        main.testCustomPoolParallelStreamWithMainThread(4, 100);
    }

    public void testExecutorService(int n, int executeTime) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = MultiThreadCalcUtilV2.asyncForEach(list, ele -> {
            printThreadInfo();
            System.out.println("=============");
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = submitOneTask(executeTime, list);
            // 使用此方法提交新任务到线程池速度要慢于理论时间，(比如线程池2个线程，单个循环1s耗时，嵌套任务执行时间总共为6s，多线程理论时间为3s)
            // 原因：父任务提交多个子任务到ForkJoinPool，当前父任务线程会阻塞等待子任务完成，而子任务并不能窃取父任务线程，因为父任务处于阻塞状态（ForkJoinPool只能窃取空闲的线程；当ForkJoinPool父任务队列满了会额外创建一个线程来处理子任务，无视设置的线程池线程数）
            // 而 submitOneTask 方法会将嵌套循环的父子任务作为同一个任务提交到ForkJoinPool线程池执行，所以符合多线程理论执行时间
//            List<Integer> integerList = submitMultiTasks(executeTime, list);
            return integerList.get(0);
        }); // 将父子任务都放入同一个线程池
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private List<Integer> submitMultiTasks(int executeTime, List<Integer> list) {
        return MultiThreadCalcUtilV2.asyncForEach(list, cEle -> {
            printThreadInfo();
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return cEle;
        });
    }

    private List<Integer> submitOneTask(int executeTime, List<Integer> list) {
        return list.parallelStream().map(cEle -> {
            printThreadInfo();
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return cEle;
        }).collect(Collectors.toList());
    }

    /**
     * 测试 自定义ForkJoinPool线程数（方法一）
     * @param n
     * @param executeTime
     */
    public void testSetThreadParallelStream(int n, int executeTime) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = executeTask(executeTime, list);
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    /**
     * 测试 自定义ForkJoinPool线程数（方法二）
     * @param n
     * @param executeTime
     */
    public void testCustomPoolParallelStream(int n, int executeTime) {
        ForkJoinPool customPool = new ForkJoinPool(4);
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        ForkJoinTask<List<Integer>> task = customPool.submit(() -> {
            return executeTask(executeTime, list);
        });
        task.join();
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    /**
     * 测试 自定义ForkJoinPool线程数（允许主线程继续执行其他任务写法）
     * @param n
     * @param executeTime
     */
    public void testCustomPoolParallelStreamWithMainThread(int n, int executeTime) {
        ForkJoinPool customPool = new ForkJoinPool(4);
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        customPool.execute(() -> {
            // 在主线程内执行的任务
            System.out.println("Main thread task");
            // 在任务内部调用其他任务
            List<Integer> resultList = customPool.invoke(new RecursiveTask<List<Integer>>() {
                @Override
                protected List<Integer> compute() {
                    return executeTask(executeTime, list);
                }
            });
            // 主线程继续执行其他工作
            System.out.println("Main thread continues");
            // 输出子任务的结果
        });
        // 主线程继续执行其他工作
        System.out.println("Main thread continues");

        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private List<Integer> executeTask(int executeTime, List<Integer> list) {
        return list.parallelStream().map(ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = list.parallelStream().map(cEle -> {
                try {
                    printThreadInfo();
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            }).collect(Collectors.toList());

            return integerList.get(0);
        }).collect(Collectors.toList());
    }

    private void printThreadInfo() {
        Thread thread = Thread.currentThread();
        System.out.println("线程：" + thread.getName() + ", id=" + thread.getId() + ", state=" + thread.getState());
    }

    private void calSomething(int executeTime) {
        long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < executeTime * 1000000; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

}
