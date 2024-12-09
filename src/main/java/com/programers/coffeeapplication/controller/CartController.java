package com.programers.coffeeapplication.controller;

import com.programers.coffeeapplication.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    @ResponseBody
    public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
        cartService.addItem(productId, quantity);
        return "success";
    }

    @PostMapping("/cart/remove")
    @ResponseBody
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeItem(productId);
        return "success";
    }

    @GetMapping("/cart/items")
    @ResponseBody
    public Map<String, Object> getCartItems() {
        Map<String, Object> cartData = new HashMap<>();
        cartData.put("items", cartService.getItems());
        cartData.put("total", cartService.getTotalAmount());
        return cartData;
    }
}