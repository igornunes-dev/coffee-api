package com.example.coffe.dtos;


import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public record PurchaseResponseDto(
        UUID id,
        Integer quantity,
        LocalTime date_purchase,
        UserResponseDto user,
        Set<CoffeeResponseDto> coffees
) { }
