package com.programers.coffeeapplication.controller;

import com.programers.coffeeapplication.dto.OrderRequestDto;
import com.programers.coffeeapplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    @ResponseBody
    public String createOrder(@RequestBody OrderRequestDto orderRequest) {
        orderService.createOrder(orderRequest);
        return "success";
    }
}