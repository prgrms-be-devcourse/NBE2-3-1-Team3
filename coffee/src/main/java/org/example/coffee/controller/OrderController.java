package org.example.coffee.controller;

import org.example.coffee.domain.Order;
import org.example.coffee.domain.OrderDetail;
import org.example.coffee.dto.OrderRequest;
import org.example.coffee.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public int createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    // 배송 완료 주문 조회
    @GetMapping("/orders/completed")
    public List<Order> getCompletedOrders() {
        return orderService.getCompletedOrders();
    }

    // 배송 예정 주문 조회
    @GetMapping("/orders/scheduled")
    public List<Order> getScheduledOrders() {
        return orderService.getScheduledOrders();
    }

    // 결제 완료 주문 조회
    @GetMapping("/orders/payment-completed")
    public List<Order> getPaymentCompletedOrders() {
        return orderService.getPaymentCompletedOrders();
    }

    //이메일로 주문 조회
    @GetMapping("/orders/mail")
    public List<Order> getOrdersByEmail(@RequestParam("email") String email) {
        return orderService.getOrdersByEmail(email);
    }



}
