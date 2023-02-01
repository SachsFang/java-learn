package com.fang.micro.impl.order;

import com.fang.micro.api.order.OrderService;
import com.fang.micro.api.order.pojo.Order;
import com.fang.micro.impl.order.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/26 10:13
 */
@Service
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
