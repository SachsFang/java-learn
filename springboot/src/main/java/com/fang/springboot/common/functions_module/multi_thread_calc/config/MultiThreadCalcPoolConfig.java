package com.fang.springboot.common.functions_module.multi_thread_calc.config;

import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

/**
 * @author shaobin
 * @date 2024/5/13 10:38
 */
@Configuration
public class MultiThreadCalcPoolConfig {

    @Value("${multi-thread-calc.pool.core-size:}")
    private Integer corePoolSize;

    @Bean("multiThreadCalcPool")
    public ForkJoinPool multiThreadCalcPool() {
        ForkJoinPool multiThreadCalcPool = new ForkJoinPool(Objects.nonNull(corePoolSize) ?
                corePoolSize : Runtime.getRuntime().availableProcessors() - 1);
        MultiThreadCalcUtilHelper.setMainCalcThreadPool(multiThreadCalcPool);
        return multiThreadCalcPool;
    }

}
