package com.example.coffeeboardproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias(value = "coffeeto")
public class Coffee {

    private int coffee_id;
    private String coffee_name;
    private int coffee_price;
}
