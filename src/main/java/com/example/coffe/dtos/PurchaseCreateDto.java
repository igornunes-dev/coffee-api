package com.example.coffe.dtos;

import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record PurchaseCreateDto(
        Map<String, Integer> coffeQuantities,
        String emailUser
) {}