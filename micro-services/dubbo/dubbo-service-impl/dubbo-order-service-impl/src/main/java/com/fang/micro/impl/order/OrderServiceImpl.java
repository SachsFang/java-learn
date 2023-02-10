package com.fang.micro.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.fang.micro.api.order.OrderService;
import com.fang.micro.api.order.pojo.Order;
import com.fang.micro.impl.order.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/26 10:13
 */
@Service // 这里要用dubbo提供的@Service注解
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> getOrderList() {
        return orderDAO.getOrderList();
    }

    @Override
    public int insertOrder(Order order) {
        return orderDAO.insertOrder(order);
    }

}
