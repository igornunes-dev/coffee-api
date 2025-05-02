package com.example.coffe.services;

import com.example.coffe.dtos.PurchaseCreateDto;
import com.example.coffe.dtos.UserCreateDto;
import com.example.coffe.dtos.UserResponseDto;
import com.example.coffe.exceptions.EmailAlreadyExistsException;
import com.example.coffe.mappers.UserMapper;
import com.example.coffe.models.CoffeeModel;
import com.example.coffe.models.PurchaseModel;
import com.example.coffe.models.UserModel;
import com.example.coffe.repositories.CoffeeRepository;
import com.example.coffe.repositories.PurchaseRepository;
import com.example.coffe.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private PurchaseRepository purchaseRepository;
    private CoffeeRepository coffeeRepository;

    public UserService(UserRepository userRepository, PurchaseRepository purchaseRepository, CoffeeRepository coffeeRepository) {
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        UserModel userModel = UserMapper.toModel(userCreateDto);
        if(userRepository.existsByEmail(userCreateDto.email())) {
            throw new EmailAlreadyExistsException("email already registered");
        }
        userModel = userRepository.save(userModel);
        return UserMapper.toDto(userModel);
    }

    public UserResponseDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public String deleteUser(UUID id) {
        userRepository.deleteById(id);
        return "Delete completed";
    }

    public UserResponseDto getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDto).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
