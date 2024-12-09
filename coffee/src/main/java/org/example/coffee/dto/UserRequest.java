package org.example.coffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String address;
    private String zipcode;
}
