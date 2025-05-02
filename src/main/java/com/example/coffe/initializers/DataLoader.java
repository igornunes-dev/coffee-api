package com.example.coffe.initializers;

import com.example.coffe.models.CoffeeModel;
import com.example.coffe.models.UserModel;
import com.example.coffe.repositories.CoffeeRepository;
import com.example.coffe.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner seedCoffeeData(CoffeeRepository coffeeRepository) {
        return args -> {
            if (coffeeRepository.count() == 0) {
                coffeeRepository.save(new CoffeeModel("Expresso", "Café forte e encorpado", new BigDecimal("5.00"), 100, null));
                coffeeRepository.save(new CoffeeModel("Latte", "Café com leite vaporizado", new BigDecimal("6.50"), 80, null));
                coffeeRepository.save(new CoffeeModel("Cappuccino", "Café com espuma de leite", new BigDecimal("7.00"), 50, null));
            }
        };
    }
}
