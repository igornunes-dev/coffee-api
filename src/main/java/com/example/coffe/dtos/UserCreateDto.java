package com.example.coffe.dtos;

import java.math.BigDecimal;

public record UserCreateDto(String name, String email, String password, BigDecimal balance) { }
