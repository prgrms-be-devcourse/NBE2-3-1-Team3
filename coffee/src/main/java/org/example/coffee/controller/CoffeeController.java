package org.example.coffee.controller;


import org.example.coffee.domain.Coffee;
import org.example.coffee.service.CoffeeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/coffees")
    public List<Coffee> getCoffees(){
        return coffeeService.getCoffees();
    }

}
