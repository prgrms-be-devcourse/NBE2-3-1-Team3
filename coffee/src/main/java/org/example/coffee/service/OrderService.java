package org.example.coffee.service;

import jakarta.transaction.Transactional;
import org.example.coffee.domain.Coffee;
import org.example.coffee.domain.Order;
import org.example.coffee.domain.OrderDetail;
import org.example.coffee.dto.OrderRequest;
import org.example.coffee.repository.CoffeeRepository;
import org.example.coffee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeRepository coffeeRepository;

    public OrderService(OrderRepository orderRepository, CoffeeRepository coffeeRepository) {
        this.orderRepository = orderRepository;
        this.coffeeRepository = coffeeRepository;
    }

    @Transactional
    public int createOrder(OrderRequest orderRequest) {
        // 1. Order 생성하고 저장
        Order order = new Order();
        order.setEmail(orderRequest.getEmail());
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setOrderDate(orderRequest.getOrderDate());

       // order.setAddress(orderRequest.getAddress());
        //order.setPostcode(orderRequest.getPostcode());

        orderRepository.save(order);

        // 2. OrderDetail 생성 및 저장
        for (OrderRequest.OrderDetailRequest detailRequest : orderRequest.getOrderDetails()) {
            // CoffeeId로 coffee 존재하는지 조회
            Coffee coffee = coffeeRepository.findById(detailRequest.getCoffeeId());

            // OrderDetail 생성
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setCoffee(coffee); // 연관 관계 설정
            orderDetail.setQuantity(detailRequest.getQuantity());


            // OrderDetail 저장
            orderRepository.saveOrderDetail(orderDetail);
        }
        return order.getOrderId();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    ///////////////////

    public List<Order> getCompletedOrders() {
        // 어제 오후 2시 이전
        LocalDateTime yesterdayTwoPM = getYesterdayTwoPM();
        return orderRepository.findOrdersBefore(yesterdayTwoPM);
    }

    public List<Order> getScheduledOrders() {
        // 어제 오후 2시 이후부터 오늘 오후 2시 미만
        LocalDateTime yesterdayTwoPM = getYesterdayTwoPM();
        LocalDateTime todayTwoPM = getTodayTwoPM();
        return orderRepository.findOrdersBetween(yesterdayTwoPM, todayTwoPM);
    }

    public List<Order> getPaymentCompletedOrders() {
        // 오늘 오후 2시 이후
        LocalDateTime todayTwoPM = getTodayTwoPM();
        return orderRepository.findOrdersAfter(todayTwoPM);
    }

    private LocalDateTime getYesterdayTwoPM() {
        LocalDateTime now = LocalDateTime.now();
        return now.minusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0);  // 어제 2시
    }

    private LocalDateTime getTodayTwoPM() {
        LocalDateTime now = LocalDateTime.now();
        return now.withHour(14).withMinute(0).withSecond(0).withNano(0);  //오늘 2시
    }


    public List<Order>getOrdersByEmail(String email) {
        return orderRepository.find(email);
    }


}
