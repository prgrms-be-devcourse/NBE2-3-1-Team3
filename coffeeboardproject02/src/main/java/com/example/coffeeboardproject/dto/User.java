package com.example.coffeeboardproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias( value = "userto" )
@Getter
@Setter
public class User {
    //#{email}, #{address}, #{zipcode}
    private String email;
    private String address;
    private String zipcode;
}
