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
     * @SentinelResource blockHandler-针对BlockException异常回调的方法    fallback-针对所有所有类型的异常回调的方法
     * @return
     */
    @RequestMapping(value = "/insertUser")
    @SentinelResource(value = INSERT_USER_RESOURCE, blockHandler = "insertUserBlockHandler")
    public String currentLimitAnnotationTest(String userName) {
        return "插入成功，插入记录为用户：" + userName;
    }

    /**
     * 限流 - 注解方式（不在代码中配置，在sentinel控制台中配置，ps:sentinel默认没有持久化，默认从注册的服务中拿取规则数据，可持久化到nacos）
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

    @RequestMapping(value = "/degrade")
    @SentinelResource(value = "degrade", blockHandler = "deGradeBack")
    public String downLevel() {
        callRemoteService();
        return "ok";
    }

    // 模拟远程服务调用，可能会抛出异常或返回错误结果
    private static Object callRemoteService() {
        // 这里只是模拟，实际情况中应该是调用远程服务
        if (Math.random() < 1) {
            // 模拟 100% 的请求失败
            throw new RuntimeException("远程服务调用失败");
        }
        return "成功调用远程服务";
    }

    // 降级逻辑
    public String deGradeBack(BlockException blockException) {// 必须声明public、String，参数之一为BlockException才能生效
        // 这里可以执行一些默认的返回值、缓存数据读取或者快速失败等操作
        String msg = "服务熔断，执行降级逻辑";
        System.out.println(msg);
        return msg;
    }
}
