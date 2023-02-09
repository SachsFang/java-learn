package com.fang.micro.impl.order.dao;

import com.fang.micro.api.order.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author shaobin
 * @date 2023/2/1 10:17
 */
public interface OrderDAO {

    @Select("SELECT * FROM `order`;")
    List<Order> getOrderList();

    @Insert("INSERT INTO `order` (`code`,`productId`,`amount`,`remark`) VALUES (#{order.code},#{order.productId},#{order.amount},#{order.remark});")
    int insertOrder(@Param("order") Order order);
}
