package com.example.coffe.mappers;

import com.example.coffe.dtos.CoffeeResponseDto;
import com.example.coffe.dtos.PurchaseResponseDto;
import com.example.coffe.dtos.UserResponseDto;
import com.example.coffe.models.CoffeeModel;
import com.example.coffe.models.PurchaseModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseMapper {
    public static PurchaseResponseDto toDto(PurchaseModel purchaseModel) {

        Set<CoffeeResponseDto> coffeeModelSet = purchaseModel.getCoffees().stream()
                .map(coffee -> new CoffeeResponseDto(
                        coffee.getId(),
                        coffee.getName(),
                        coffee.getDescription(),
                        coffee.getPrice(),
                        coffee.getStock()
                ))
                .collect(Collectors.toSet());


        UserResponseDto userDto = new UserResponseDto(
                purchaseModel.getUser_id().getId(),
                purchaseModel.getUser_id().getName(),
                purchaseModel.getUser_id().getEmail(),
                purchaseModel.getUser_id().getBalance(),
                new HashSet<>()
        );



        return new PurchaseResponseDto(
                purchaseModel.getId(),
                purchaseModel.getQuantity(),
                purchaseModel.getDate_purchase(),
                userDto,
                coffeeModelSet
        );
    }
}
