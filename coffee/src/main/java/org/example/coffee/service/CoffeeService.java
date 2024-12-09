package org.example.coffee.service;

import org.example.coffee.domain.Coffee;
import org.example.coffee.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }
}
