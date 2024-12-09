package com.programers.coffeeapplication.mapper;

import com.programers.coffeeapplication.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findById(Long productId);
    void insert(Product product);
}