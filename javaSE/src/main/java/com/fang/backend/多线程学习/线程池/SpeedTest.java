package com.fang.backend.多线程学习.线程池;

import com.fang.springboot.common.util.MultiThreadCalcUtil;
import com.fang.springboot.common.util.NormalMultiThreadCalcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2023/10/13 10:08
 */
public class SpeedTest {
    public static void main(String[] args) {
        System.out.print("main线程：");
        printThreadInfo();
        // 两个线程，运行4个10秒的程序快还是1000个40毫秒秒的程序快
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 结论是后者快些
//        execute(4, 10000);
//        execute(1000, 40);
        /* 测试同数据量下单循环快还是多循环快 */
        // 普通双循环
//        testDoubleNormalCycle(4, 1000);//20s
        // 普通线程池双循环
//        testDoubleCycleWithNormalPool(4, 1000); //20s 此行需要调整两线程池做大核心数为1
        // 普通线程池双循环单多线程循环
//        testDoubleCycleNoThreadCycleWithNormalPool(4, 1000); //12s
        // ForkJoin线程池双循环
        // 14s怎么来？一个五个大任务。首先提交一个任务给一个线程，剩下一个线程，这个任务里面有4个循环也就是4个任务，再提交一个任务给线程池，那么就剩3个任务，
        // 还有3个任务需要提交，此时线程1在提交第二个任务，线程2在处理第二个任务，此时线程池没有可用线程，发生阻塞，阻塞需要另外创建一个线程3（无视线程数限制）来打破僵局，
        // 创建了线程3，第三个任务给到线程3，此时还有2个任务需要提交；如果此时没有任务执行完，则会再创建一个线程4给任务4，同理创建线程至直到所有任务提交完毕，
        // 如果任务执行完的话则会将任务提交给空闲线程不创建新线程。因此由于线程阻塞资源竞争会导致实际速度会比理论速度要慢
        testDoubleCycle(4, 1000);//14s，此个执行过程最大有创建5个线程执行。线上cpu彪到100%也可能是因为这个原因
        // ForkJoin线        程池双循环单多线程循环
//        testDoubleCycleNoThreadCycleOne(4, 1000);//12s
    }

    private static void testDoubleCycle(int n, int executeTime) {
        // 双循环
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = MultiThreadCalcUtil.asyncForEach(list, ele -> {
            printThreadInfo();
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = MultiThreadCalcUtil.asyncForEach(list, cEle -> {
                printThreadInfo();
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            });
            return integerList.get(0);
        });
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private static void testDoubleCycleNoThreadCycleOne(int n, int executeTime) {
        // 双循环
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = list.stream().map(ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = MultiThreadCalcUtil.asyncForEach(list, cEle -> {
                printThreadInfo();
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            });
            return integerList.get(0);
        }).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private static void testDoubleNormalCycle(int n, int executeTime) {
        // 双循环
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = list.stream().map(ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = list.stream().map(cEle -> {
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            }).collect(Collectors.toList());
            return integerList.get(0);
        }).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private static void testDoubleCycleNoThreadCycleWithNormalPool(int n, int executeTime) {
        // 双循环
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = list.stream().map(ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = NormalMultiThreadCalcUtil.asyncForEach(list, cEle -> {
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            });
            return integerList.get(0);
        }).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private static void testDoubleCycleWithNormalPool(int n, int executeTime) {
        // 双循环
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        List<Integer> integers = NormalMultiThreadCalcUtil.asyncForEach(list, ele -> {
            try {
                Thread.sleep(executeTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Integer> integerList = NormalMultiThreadCalcUtil.asyncForEach(list, cEle -> {
                try {
                    Thread.sleep(executeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return cEle;
            }, 1);
            return integerList.get(0);
        }, 0);
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private static void execute(int n, int executeTime) {
        // 单循环
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

    private static void printThreadInfo() {
        Thread thread = Thread.currentThread();
        System.out.println("线程：" + thread.getName() + ", id=" + thread.getId() + ", state=" + thread.getState());
    }

}
