package com.example.coffeeboardproject.mapper;

import com.example.coffeeboardproject.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int user_insert(User user);
}
