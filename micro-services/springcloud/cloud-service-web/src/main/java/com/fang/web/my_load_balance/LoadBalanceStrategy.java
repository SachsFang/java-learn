package com.fang.web.my_load_balance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡策略抽象类
 * @author shaobin
 * @date 2022/12/29 15:51
 */
public abstract class LoadBalanceStrategy {
    public abstract ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList);
}
