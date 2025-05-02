package com.example.coffe.services;

import com.example.coffe.dtos.CoffeeResponseDto;
import com.example.coffe.mappers.CoffeeMapper;
import com.example.coffe.mappers.UserMapper;
import com.example.coffe.models.CoffeeModel;
import com.example.coffe.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<CoffeeResponseDto> getAllCoffee() {
        return coffeeRepository.findAll().stream().map(CoffeeMapper::toDto).collect(Collectors.toList());
    }

    public Set<CoffeeResponseDto> getCoffeesByNames(Set<String> coffeeNames) {
        List<CoffeeModel> coffeeList = coffeeRepository.findByNameIn(coffeeNames);

        if (coffeeList.isEmpty()) {
            throw new RuntimeException("Coffees not found for the given names");
        }

        return coffeeList.stream()
                .map(CoffeeMapper::toDto)
                .collect(Collectors.toSet());
    }
}

