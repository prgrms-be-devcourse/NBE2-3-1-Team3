package com.programers.coffeeapplication.mapper;

import com.programers.coffeeapplication.domain.Order;
import com.programers.coffeeapplication.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);
    Order findById(Long orderId);
}