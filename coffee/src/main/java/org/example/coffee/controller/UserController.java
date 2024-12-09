package org.example.coffee.controller;

import org.example.coffee.dto.UserRequest;
import org.example.coffee.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
    }
}
