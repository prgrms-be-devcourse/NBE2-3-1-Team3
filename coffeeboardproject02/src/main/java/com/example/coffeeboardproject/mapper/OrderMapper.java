package com.example.coffeeboardproject.mapper;

import com.example.coffeeboardproject.dto.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    Order order_select(Order order);
    int order_insert(Order order);
    int getLastInsertId();
}
