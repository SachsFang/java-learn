package 后端.Java常用设计模式.单例模式;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SachsFang on 2021/7/14 20:32
 */
public class Main {
    public static void main(String[] args) {
//        Bus bus = Bus.getInstance();
        Car car = Car.getInstance();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bus bus = Bus.currentGetInstance();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Bus bus = Bus.currentGetInstance();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Bus bus = Bus.currentGetInstance();
            }
        });
        executor.shutdown();
    }
}
