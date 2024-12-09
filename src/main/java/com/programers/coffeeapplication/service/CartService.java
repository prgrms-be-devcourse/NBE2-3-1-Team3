package com.programers.coffeeapplication.service;

import com.programers.coffeeapplication.domain.Product;
import com.programers.coffeeapplication.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartService {
    private final List<CartItemDto> items = new ArrayList<>();
    private final ProductService productService;

    public void addItem(Long productId, int quantity) {
        Product product = productService.getProduct(productId);
        CartItemDto existingItem = findCartItem(productId);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            items.add(new CartItemDto(product, quantity));
        }
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private CartItemDto findCartItem(Long productId) {
        return items.stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}