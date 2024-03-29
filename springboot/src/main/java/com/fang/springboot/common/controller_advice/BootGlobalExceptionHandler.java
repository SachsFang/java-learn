package com.fang.springboot.common.controller_advice;

import com.fang.springboot.common.builder.BaseRespBuilder;
import com.fang.springboot.common.pojo.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理器
 * @author shaobin
 * @date 2022/10/10 11:40
 */
@ControllerAdvice
@Slf4j
public class BootGlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResp runTimeExceptionHandler(RuntimeException e) {
        log.error("error:", e);
        return BaseRespBuilder.fail().setMsg(e.toString()).build();
    }

}
