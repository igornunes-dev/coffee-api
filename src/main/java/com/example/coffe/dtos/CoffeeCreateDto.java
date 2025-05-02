package com.example.coffe.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record CoffeeCreateDto(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        UUID purchaseId
) {}
