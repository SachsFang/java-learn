package com.fang.springboot.user.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/10/9 17:50
 */
@Component
@Slf4j
public class UserEventListener {

    @Async("taskExecutor")
    public void sendSMS() throws InterruptedException {
        log.info("<3>向用户发送短信中...");
        Thread.sleep(3000);
        log.info("<3>短信发送成功！");
    }
}
