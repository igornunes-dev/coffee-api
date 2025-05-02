package com.example.coffe.mappers;

import com.example.coffe.dtos.CoffeeResponseDto;
import com.example.coffe.models.CoffeeModel;

import java.math.BigDecimal;
import java.util.UUID;

public class CoffeeMapper {
    public static CoffeeResponseDto toDto(CoffeeModel coffeeModel) {
        return new CoffeeResponseDto(
                coffeeModel.getId(),
                coffeeModel.getName(),
                coffeeModel.getDescription(),
                coffeeModel.getPrice(),
                coffeeModel.getStock()
        );
    }
}
