package com.example.coffe.services;

import com.example.coffe.dtos.PurchaseCreateDto;
import com.example.coffe.dtos.PurchaseResponseDto;
import com.example.coffe.dtos.UserResponseDto;
import com.example.coffe.exceptions.InsufficientMoneyException;
import com.example.coffe.exceptions.InsufficientStockException;
import com.example.coffe.mappers.PurchaseMapper;
import com.example.coffe.mappers.UserMapper;
import com.example.coffe.models.CoffeeModel;
import com.example.coffe.models.PurchaseModel;
import com.example.coffe.models.UserModel;
import com.example.coffe.repositories.CoffeeRepository;
import com.example.coffe.repositories.PurchaseRepository;
import com.example.coffe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PurchaseResponseDto> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(PurchaseMapper::toDto).collect(Collectors.toList());
    }

    public PurchaseResponseDto createPurchase(PurchaseCreateDto purchaseCreateDto) {
        UserModel userModel = userRepository.findByEmail(purchaseCreateDto.emailUser())
                .orElseThrow(() -> new RuntimeException("user not found"));

        PurchaseModel purchaseModel = new PurchaseModel();

        purchaseModel.setUser_id(userModel);
        purchaseModel.setDate_purchase(LocalTime.now());

        Map<String, Integer> coffeeMap = purchaseCreateDto.coffeQuantities();

        BigDecimal total = BigDecimal.ZERO;

        Set<CoffeeModel> coffees = new HashSet<>(coffeeRepository.findByNameIn(coffeeMap.keySet()));

        for (CoffeeModel coffee : coffees) {
            Integer quantityPurchased = coffeeMap.get(coffee.getName());

            if(coffee.getStock() == 0) {
                throw new InsufficientStockException("coffee" + coffee.getName() + "is out of stock");
            }

            if(coffee.getStock() < quantityPurchased) {
                throw new InsufficientStockException("stock insufficient");
            }

            BigDecimal subTotal = coffee.getPrice().multiply(BigDecimal.valueOf(quantityPurchased));
            total = total.add(subTotal);

            coffee.setStock(coffee.getStock() - quantityPurchased);

            coffeeRepository.save(coffee);
        }

        int totalQuantity = coffeeMap.values().stream().mapToInt(Integer::intValue).sum();

        purchaseModel.setCoffees(coffees);
        purchaseModel.setQuantity(totalQuantity);

        if(userModel.getBalance().compareTo(total) < 0) {
            throw new InsufficientMoneyException("money insufficient");
        }

        BigDecimal updatedBalance = userModel.getBalance().subtract(total);
        userModel.setBalance(updatedBalance);

        userRepository.save(userModel);

        purchaseRepository.save(purchaseModel);

        return PurchaseMapper.toDto(purchaseModel);
    }



}
