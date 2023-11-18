package com.fang.backend.多线程学习.线程池;

import com.fang.springboot.common.util.MultiThreadCalcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shaobin
 * @date 2023/10/13 10:08
 */
public class SpeedTest {
    public static void main(String[] args) {
        // 两个线程，运行4个10秒的程序快还是1000个40毫秒秒的程序快

        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 结论是后者快些
        execute(4, 10000);
        execute(1000, 40);
    }

    private static void execute(int n, int executeTime) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = MultiThreadCalcUtil.asyncForEach(list, ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return ele;
        });
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }


}
