package com.fang.micro.impl.order;

import com.fang.micro.api.order.OrderService;
import com.fang.micro.api.order.pojo.Order;
import org.springframework.stereotype.Service;

/**
 * @author shaobin
 * @date 2022/12/26 10:13
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrderInfo() {
        Order order = new Order();
        order.setCode("111");
        order.setRemark("订单1");
        return order;
    }
}
