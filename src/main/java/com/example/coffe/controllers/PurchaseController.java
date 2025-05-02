package com.example.coffe.controllers;

import com.example.coffe.dtos.PurchaseCreateDto;
import com.example.coffe.dtos.PurchaseResponseDto;
import com.example.coffe.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDto>> getAllPurchase() {
        List<PurchaseResponseDto> purchaseResponseDtos = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchaseResponseDtos);
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDto> createPurchase(@RequestBody PurchaseCreateDto purchaseCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.createPurchase(purchaseCreateDto));
    }
}
