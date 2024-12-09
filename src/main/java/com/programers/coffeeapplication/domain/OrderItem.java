package com.programers.coffeeapplication.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}