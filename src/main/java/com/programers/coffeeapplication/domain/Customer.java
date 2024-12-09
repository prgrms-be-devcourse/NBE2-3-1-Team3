package com.programers.coffeeapplication.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Customer {
    private String email;
    private String address;
    private String zipcode;
    private LocalDateTime createdAt;
}
