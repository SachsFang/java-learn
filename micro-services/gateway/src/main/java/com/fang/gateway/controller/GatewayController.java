package com.fang.gateway.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2023/1/16 10:17
 */
@RestController
@RequestMapping("/gateway")
@Slf4j
public class GatewayController {

    private final static String GET_USER_RESOURCE = "getUser";

    private final static String INSERT_USER_RESOURCE = "insertUser";

    /**
     * 限流 - api方式
     * @return
     */
    @RequestMapping(value = "/getUser")
    public String currentLimitApiTest() {
        Entry entry = null;
        try {
            entry = SphU.entry(GET_USER_RESOURCE);
            return "访问成功，用户名称：fang";
        } catch (BlockException e) {
            return "服务器忙，请稍后再试！";
        } finally {
            if (entry != null) {
                entry.close();
            }
        }
    }

    /**
     * 限流 - 注解方式
     * @SentinelResource blockHandler-限流、熔断出现异常执行的方法    fallback-服务降级（限流、熔断、接口超时、接口出现异常）执行的方法
     * @return
     */
    @RequestMapping(value = "/insertUser")
    @SentinelResource(value = INSERT_USER_RESOURCE, blockHandler = "insertUserBlockHandler")
    public String currentLimitAnnotationTest(String userName) {
        return "插入成功，插入记录为用户：" + userName;
    }

    /**
     * 限流 - 注解方式（不在代码中配置，在sentinel控制台中配置，ps:sentinel默认没有持久化）
     */
    @RequestMapping(value = "/test")
    @SentinelResource(value = "test", blockHandler = "noParamBlockHandler")
    public String test() {
        return "OK";
    }

    /**
     * 限流 - 注解方式（限制单个接口线程数测试）
     */
    @RequestMapping(value = "/testSemaphore")
    @SentinelResource(value = "testSemaphore", blockHandler = "noParamBlockHandler")
    public String testSemaphore() {
        try {
            // 模拟业务处理
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(Thread.currentThread().getName());
        return "OK";
    }

    /**
     * 插入用户限流阻塞处理方法
     * ps:回调方法必须和求情限流方法的访问权限、参数类型、返回类型都要一致；因为底层通过反射取找blockHandler的方法时是以请求限流方法的格式一致，并且参数都要在blockException参数之前
     * @return
     */
    public String insertUserBlockHandler(String userName, BlockException blockException) {
        return "当前访问人数过多，请稍后重试！，错误信息为；" + blockException;
    }

    public String noParamBlockHandler(BlockException blockException) {
        return "当前访问人数过多，请稍后重试！，错误信息为；" + blockException;
    }
}
