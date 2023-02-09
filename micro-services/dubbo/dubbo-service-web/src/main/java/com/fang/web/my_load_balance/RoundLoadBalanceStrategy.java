package com.fang.web.my_load_balance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询策略
 * @author shaobin
 * @date 2022/12/29 16:05
 */
public class RoundLoadBalanceStrategy extends LoadBalanceStrategy {

    private static AtomicInteger atomicCount = new AtomicInteger();

    @Override
    public ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList) {
        if (CollectionUtils.isEmpty(serviceInstanceList)) {
            return null;
        }
        int index = atomicCount.incrementAndGet() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
