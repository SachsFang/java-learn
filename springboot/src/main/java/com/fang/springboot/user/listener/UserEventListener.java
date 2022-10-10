package com.fang.springboot.user.listener;

import com.fang.springboot.user.pojo.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/10/9 17:50
 */
@Component
@Slf4j
public class UserEventListener {

    @Async("taskExecutor") // 当只配置了一个线程池时，"taskExecutor"可以不写，spring会默认找一个TaskExecutor
    public void sendSMS() throws InterruptedException {
        log.info("<3>向用户发送短信中...") ;
        Thread.sleep(3000);
        log.info("<3>短信发送成功！");
    }

    @Async // 当只配置了一个线程池时，"taskExecutor"可以不写，spring会默认找一个TaskExecutor
    @EventListener
    public void sendSMSByListener(UserEvent userEvent) throws InterruptedException {
        log.info(userEvent.getName() + ":" + userEvent.getMsg());
        log.info("<3>向用户发送短信中...") ;
        Thread.sleep(3000);
        log.info("<3>短信发送成功！");
    }
}
