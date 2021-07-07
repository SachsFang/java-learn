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
        var THREAD_SIZE = 6;
        Date dateStart = new Date();
        ExecutorService executor = Executors.newFixedThreadPool(3);
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
        System.out.println("程序执行结束,执行所用时间: "+(dateEnd.getTime()-dateStart.getTime()));
    }
}
