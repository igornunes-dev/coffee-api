package com.example.coffe.controllers;

import com.example.coffe.dtos.UserCreateDto;
import com.example.coffe.dtos.UserResponseDto;
import com.example.coffe.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userCreateDto));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @DeleteMapping("/deleteFromUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }
}
