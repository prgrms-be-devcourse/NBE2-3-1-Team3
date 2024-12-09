package com.programers.coffeeapplication.service;

import com.programers.coffeeapplication.domain.Customer;
import com.programers.coffeeapplication.domain.Order;
import com.programers.coffeeapplication.domain.OrderItem;
import com.programers.coffeeapplication.dto.OrderRequestDto;
import com.programers.coffeeapplication.mapper.CustomerMapper;
import com.programers.coffeeapplication.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final CartService cartService;

    @Transactional
    public Order createOrder(OrderRequestDto orderRequest) {
        // 고객 정보 저장
        Customer customer = new Customer();
        customer.setEmail(orderRequest.getEmail());
        customer.setAddress(orderRequest.getAddress());
        customer.setZipcode(orderRequest.getZipcode());

        if (customerMapper.findByEmail(customer.getEmail()) == null) {
            customerMapper.insert(customer);
        }

        // 주문 생성
        Order order = new Order();
        order.setCustomerEmail(customer.getEmail());
        order.setTotalAmount(cartService.getTotalAmount());
        order.setStatus("PENDING");

        orderMapper.insertOrder(order);

        // 주문 상품 저장
        cartService.getItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setProductId(cartItem.getProduct().getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderMapper.insertOrderItem(orderItem);
        });

        // 장바구니 비우기
        cartService.clear();

        return order;
    }
}