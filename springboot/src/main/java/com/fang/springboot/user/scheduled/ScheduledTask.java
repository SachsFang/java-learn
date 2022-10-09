package com.fang.springboot.user.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/10/9 16:47
 */
@Component
public class ScheduledTask {

    private long executeCount = 0;

    /**
     * 每三秒执行一次
     */
    @Scheduled(fixedRate = 3000)
    public void task() {
        System.out.println("定时任务执行 " + ++executeCount + "次");
    }
}
