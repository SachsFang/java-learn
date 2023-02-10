package com.fang.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fang.micro.api.order.OrderService;
import com.fang.micro.api.order.pojo.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/26 10:57
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @GetMapping("getOrderInfoList")
    public List<Order> getOrderInfoList() {
        return orderService.getOrderList();
    }
}
