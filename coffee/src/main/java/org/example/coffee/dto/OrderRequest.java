package org.example.coffee.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private String email; // Order의 email
    private int totalPrice; // Order의 totalPrice
    private LocalDateTime orderDate;
    private String address;
    private String postcode;
    private List<OrderDetailRequest> orderDetails; // OrderDetail의 리스트

    @Getter
    @Setter
    public static class OrderDetailRequest {
        private int coffeeId; // Coffee의 ID
        private int quantity; // 수량

    }
}

