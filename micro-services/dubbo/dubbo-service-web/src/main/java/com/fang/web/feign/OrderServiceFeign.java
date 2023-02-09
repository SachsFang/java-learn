package com.fang.web.feign;

import com.fang.micro.api.order.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shaobin
 * @date 2022/12/29 17:31
 */
@FeignClient(name = "order-micro-service", path = "/learn")
public interface OrderServiceFeign extends OrderService {
}
