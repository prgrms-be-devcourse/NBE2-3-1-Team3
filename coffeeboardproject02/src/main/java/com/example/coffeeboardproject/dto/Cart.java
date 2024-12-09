package com.example.coffeeboardproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias(value = "cartto")
public class Cart {
    private int orderdetail_id;
    private int quantity;
    private int order_id;
    private int coffee_id;

    private List<Coffee> coffee;
}
