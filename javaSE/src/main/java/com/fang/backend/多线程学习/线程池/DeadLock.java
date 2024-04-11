package com.fang.backend.多线程学习.线程池;

import com.fang.springboot.common.util.MultiThreadCalcUtilV1;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2023/10/19 11:00
 */
public class DeadLock {

    @SneakyThrows
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.testExecutorService(4, 100);
    }

    public void testExecutorService(int n, int executeTime) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = MultiThreadCalcUtilV1.asyncForEach(list, ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printThreadInfo();
            System.out.println("=============");
            List<Integer> integerList = MultiThreadCalcUtilV1.asyncForEach(list, cEle -> {
                printThreadInfo();
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            });
            return integerList.get(0);
        }); // 将父子任务都放入同一个线程池
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private void printThreadInfo() {
        Thread thread = Thread.currentThread();
        System.out.println("线程：" + thread.getName() + ", id=" + thread.getId() + ", state=" + thread.getState());
    }

}
