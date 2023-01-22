package com.fang.gateway.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2023/1/16 10:17
 */
@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @RequestMapping(value = "/getUser")
    public String currentLimitTest() {
        Entry entry = null;
        try {
            entry = SphU.entry("/getUser");
            return "访问成功，用户名称：fang";
        } catch (BlockException e) {
            return "服务器忙，请稍后再试！";
        } finally {
            if (entry != null) {
                entry.close();
            }
        }
    }
}
