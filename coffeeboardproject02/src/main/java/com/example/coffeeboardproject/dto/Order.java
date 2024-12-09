package com.example.coffeeboardproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias( value = "orderto" )
@Getter
@Setter
public class Order {
    //#{order_id}, #{email}, #{total_price}, #{order_date}
    private int order_id;
    private String email;
    private int total_price;
    private String order_date;

    // 나중에 order_id 불러오기
    private List<Cart> cart;
}
