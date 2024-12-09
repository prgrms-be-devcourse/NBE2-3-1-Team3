package com.programers.coffeeapplication.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Product {
    private Long productId;
    private String name;
    private String category;
    private BigDecimal price;
    private String imageUrl;
    private boolean available;
}
