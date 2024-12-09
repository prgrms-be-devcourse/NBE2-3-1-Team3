package com.example.coffeeboardproject.service;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Order;
import com.example.coffeeboardproject.dto.User;
import com.example.coffeeboardproject.mapper.CartMapper;
import com.example.coffeeboardproject.mapper.OrderMapper;
import com.example.coffeeboardproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;

    public Order oderSelect(Order order) {
        order = orderMapper.order_select(order);

        return order;
    }

    public int orderInsert(Order order) {
        System.out.println("orderInsert 호출");
        int flag = 1;

        int result = orderMapper.order_insert(order);

        if (result == 1 ){
            int lastOrderId = orderMapper.getLastInsertId();
            order.setOrder_id(lastOrderId);  // Order 객체에 order_id 설정
            System.out.println("Last Inserted Order ID: " + lastOrderId);
            flag = 0;
        }
        return flag;
    }

    public int userInsert(User user) {
        System.out.println("userInsert 호출");
        // 처리에 대한 결과
        int flag = 1;

        try {
            int result = userMapper.user_insert(user);

            if (result == 1) {
                flag = 0;  // 삽입 성공
            } else {
                flag = 1;  // 삽입 실패
            }
        } catch (Exception e) {
            // 예외 발생 시 flag 값을 2로 설정하거나 로그를 남기기
            System.out.println("Error: " + e.getMessage());
            flag = 2; // 예외 발생 시 flag를 2로 설정 (예외 처리 상황)
        }

        System.out.println("flag: " + flag);
        return flag;
    }

}
