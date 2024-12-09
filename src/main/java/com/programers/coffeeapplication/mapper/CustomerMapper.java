package com.programers.coffeeapplication.mapper;

import com.programers.coffeeapplication.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    Customer findByEmail(String email);
    void insert(Customer customer);
}