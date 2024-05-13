package com.fang.springboot.common.functions_module.multi_thread_calc.util;

import java.util.concurrent.ForkJoinPool;

/**
 * @author shaobin
 * @date 2024/5/13 10:41
 */
public class MultiThreadCalcUtilHelper {

    public static void setMainCalcThreadPool(ForkJoinPool executorService) {
        MultiThreadCalcUtilV3.calcWorkerExecutorPool = executorService;
    }

}
