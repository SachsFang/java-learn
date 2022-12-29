package com.fang.web.my_load_balance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * 随机策略
 * @author shaobin
 * @date 2022/12/29 16:16
 */
public class RandomLoadBalanceStrategy extends LoadBalanceStrategy {

    private static final Random random = new Random();

    @Override
    public ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList) {
        if (CollectionUtils.isEmpty(serviceInstanceList)) {
            return null;
        }
        int index = random.nextInt(serviceInstanceList.size());
        return serviceInstanceList.get(index);
    }
}
