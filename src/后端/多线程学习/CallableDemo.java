package 后端.多线程学习;

import java.util.concurrent.*;

/**
 * Created by SachsFang on 2021/7/7 10:43
 * Callable 示例
 */
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "我拿到了线程数据";
    }

    public static void main(String[] args) {
        /*第一种显式实现写法*/
        FutureTask<String> futureTask1 = new FutureTask(new CallableDemo());
        futureTask1.run();
        try {
            String o = futureTask1.get();
            System.out.println(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("线程式阻塞的,执行完futureTask1再执行futureTask2,类似于js的await");

        /*第二种匿名子类写法*/
        FutureTask<String> futureTask2 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "我拿到了线程数据-匿名子类";
            }
        });
//        futureTask2.run(); //futureTask实现了自己的run方法达到Thread.start()的效果
        Thread thread = new Thread(futureTask2);
        thread.start();
        try {
            String data = futureTask2.get();
            System.out.println(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*使用Future*/
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 创建一个单线程的线程池
        Future<String> future = executor.submit(new CallableDemo());//将Callable的实现提交到线程池,并返回future对象
        try {
            String s = future.get();//获取线程结果
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
