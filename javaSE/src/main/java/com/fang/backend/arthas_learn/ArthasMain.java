package com.fang.backend.arthas_learn;

import lombok.SneakyThrows;

/**
 * @author shaobin
 * @date 2023/10/9 17:42
 */
public class ArthasMain {

    @SneakyThrows
    public static void main(String[] args) {
        ArthasMain main = new ArthasMain();
//        main.queryThread();
//        main.seeProductionCode();
//        main.seeTrace();
//        main.deadLoop();
        main.deadLock();
    }

    private static final String A = "A";

    private static final String B = "B";

    /**
     * 查看线程
     */
    @SneakyThrows
    void queryThread() {
        running(() -> {
            System.out.println("running");
        });
    }

    /**
     * 反编译查看生产环境代码
     */
    @SneakyThrows
    void seeProductionCode() {
        DeCompileDemo deCompileDemo = new DeCompileDemo();
        deCompileDemo.print();
        Thread.sleep(600000);
    }

    /**
     * 跟踪方法
     */
    void seeTrace() {
        running(() -> {
            TraceDemo traceDemo = new TraceDemo();
            traceDemo.traceFirst("I am a good boy");
        });
    }

    /**
     * 死循环
     */
    void deadLoop() {
        while (true) {
            System.out.println("this is in dead loop");
        }
    }

    /**
     * 死锁
     */
    void deadLock() {
        // 死锁导致cpu飙高
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("线程1获取资源A");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程1尝试获取资源B");
                synchronized (B) {
                    System.out.println("线程1获取到资源B");
                }
            }
        });
        t1.setName("thread one");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("线程2获取资源B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程2尝试获取资源A");
                synchronized (A) {
                    System.out.println("线程2尝试获取资源A");
                }
            }
        });
        t2.setName("thread two");
        t2.start();
    }

    static <T> void running(Runnable runnable) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runnable.run();
            }
        });
        thread.setName("I am demo thread");
        thread.start();
    }

}
