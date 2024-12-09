package com.programers.coffeeapplication.dto;

import com.programers.coffeeapplication.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Product product;
    private int quantity;
}
