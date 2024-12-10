package com.example.coffeeboardproject.mapper;

import com.example.coffeeboardproject.dto.Coffee;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CoffeeMapper {
    List<Coffee> coffee_select();

}
