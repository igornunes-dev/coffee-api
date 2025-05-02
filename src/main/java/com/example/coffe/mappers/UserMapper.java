package com.example.coffe.mappers;

import com.example.coffe.dtos.PurchaseResponseDto;
import com.example.coffe.dtos.UserCreateDto;
import com.example.coffe.dtos.UserResponseDto;
import com.example.coffe.models.UserModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDto toDto(UserModel userModel) {
        Set<PurchaseResponseDto> purchaseResponseDtos = userModel.getPurchases().stream().map(PurchaseMapper::toDto).collect(Collectors.toSet());
        return new UserResponseDto(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getBalance(),
                purchaseResponseDtos
        );
    }
    public static UserModel toModel(UserCreateDto userCreateDto) {
        UserModel userModel = new UserModel();
        userModel.setName(userCreateDto.name());
        userModel.setEmail(userCreateDto.email());
        userModel.setBalance(userCreateDto.balance());
        userModel.setPassword(userCreateDto.password());
        return userModel;
    }
}
