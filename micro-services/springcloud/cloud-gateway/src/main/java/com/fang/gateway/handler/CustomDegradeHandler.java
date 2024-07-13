package com.fang.gateway.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaobin
 * @date 2024/6/14 14:11
 */
public class CustomDegradeHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        System.out.println("服务熔断，执行降级逻辑");
        // 这里可以执行一些默认的返回值、缓存数据读取或者快速失败等操作
    }
}
