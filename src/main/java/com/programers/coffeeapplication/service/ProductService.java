package com.programers.coffeeapplication.service;

import com.programers.coffeeapplication.domain.Product;
import com.programers.coffeeapplication.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    public Product getProduct(Long productId) {
        return productMapper.findById(productId);
    }
}