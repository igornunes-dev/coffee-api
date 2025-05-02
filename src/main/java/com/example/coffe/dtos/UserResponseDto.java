package com.example.coffe.dtos;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, BigDecimal balance, Set<PurchaseResponseDto> purchases) { }
