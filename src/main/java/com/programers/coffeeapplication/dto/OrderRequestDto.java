package com.programers.coffeeapplication.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String email;
    private String address;
    private String zipcode;
}