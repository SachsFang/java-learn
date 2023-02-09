package com.fang.micro.api.order;

import com.fang.micro.api.order.pojo.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/25 18:55
 */
@RequestMapping("/order")
@ResponseBody
public interface OrderService {
    @RequestMapping("/getOrderList")
    List<Order> getOrderList();

    @PostMapping("/insert")
    int insertOrder(@RequestBody Order order);
}
