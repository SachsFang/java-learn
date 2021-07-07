package 后端.多线程学习.线程池;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by SachsFang on 2021/7/7 14:32
 * 使用定长线程池 - 示例
 */
public class ThreadPool implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        SingleThreadExecutorDemo();
//        FixedThreadPoolDemo();
//        CacheTreadPool();
        ScheduledThreadPoolDemo();
        ;
    }

    public static void SingleThreadExecutorDemo() throws ExecutionException, InterruptedException {
        System.out.println("------------------单线程线程池测试开始------------------");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executeCode(executor, 9);
        System.out.println("------------------单线程线程池测试结束------------------");
    }

    public static void FixedThreadPoolDemo() throws ExecutionException, InterruptedException {
        System.out.println("------------------定长线程池测试开始------------------");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executeCode(executor, 9);
        System.out.println("------------------定长线程池测试结束------------------");
    }

    public static void CacheTreadPool() throws ExecutionException, InterruptedException {
        System.out.println("------------------可缓存线程池测试开始------------------");
        ExecutorService executor = Executors.newCachedThreadPool();
        executeCode(executor, 9);
        System.out.println("------------------可缓存程池测试结束------------------");
    }

    public static void ScheduledThreadPoolDemo() throws ExecutionException, InterruptedException {
        System.out.println("------------------周期性线程池测试开始------------------");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        /*周期性*/
        Future futurePeriod = executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date() + ": " + Thread.currentThread().getName()+"-周期输出");
            }
        }, 0, 5, TimeUnit.SECONDS);

        /*延时性*/
        Future<String> future = executor.schedule(new ThreadPool(), 10, TimeUnit.SECONDS);
        System.out.println(new Date() + ": " + "线程提交到了线程池");
        executeScheduledCode(executor, future);

    }

    public static void executeScheduledCode(ScheduledExecutorService executor, Future<String> future) throws ExecutionException, InterruptedException {
        Date dateStart = new Date();
        String o = future.get();
        System.out.println(new Date() + ": " + o);
        Date dateEnd = new Date();
        System.out.println("延时程序执行结束,执行所用时间: " + (dateEnd.getTime() - dateStart.getTime()));
    }


    public static void executeCode(ExecutorService executor, int threadAmount) throws ExecutionException, InterruptedException {
        var THREAD_SIZE = threadAmount;
        Date dateStart = new Date();
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < THREAD_SIZE; i++) {
            Future<String> future = executor.submit(new ThreadPool());
            futureList.add(future);
        }
        System.out.println("所有线程提交到了线程池");
        executor.shutdown();
        for (int i = 0; i < THREAD_SIZE; i++) {
            String result = futureList.get(i).get();
            System.out.println(result);
        }
        Date dateEnd = new Date();
        System.out.println("程序执行结束,执行所用时间: " + (dateEnd.getTime() - dateStart.getTime()));
    }
}
