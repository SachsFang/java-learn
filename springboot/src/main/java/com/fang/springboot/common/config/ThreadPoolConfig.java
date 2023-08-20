package com.fang.springboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shaobin
 * @date 2022/10/9 18:27
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 每秒需要多少个线程处理
     * tasks/(1/taskcost)
     */
    private int corePoolSize = 3;

    /**
     * 线程池维护线程的最大数量
     * (max(tasks)- queueCapacity)/(1/taskcost)
     */
    private int maxPoolSize = 3;

    /**
     * 缓存队列
     * (coreSizePool/taskcost)*responsetime
     */
    private int queueCapacity = 10;

    /**
     * 允许的空闲时间
     * 默认为60
     */
    private int keepAlive = 100;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置允许的空闲时间（秒）
        //executor.setKeepAliveSeconds(keepAlive);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

    /**
     * 创建线程池
     */
    @Bean("main-executorService")
    public ExecutorService mainExecutorService() {
        // 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
        int core = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(
                core/2,
                core,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                new CustomizableThreadFactory("main-thread-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 创建线程池
     */
    @Bean("worker-executorService")
    public ExecutorService workerExecutorService() {
        // 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
        int core = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(
                core/2,
                core,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                new CustomizableThreadFactory("worker-thread-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}


