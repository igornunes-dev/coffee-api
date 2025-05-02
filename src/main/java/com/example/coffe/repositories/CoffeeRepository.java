package com.example.coffe.repositories;

import com.example.coffe.models.CoffeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CoffeeRepository extends JpaRepository<CoffeeModel, UUID> {
    List<CoffeeModel> findByNameIn(Set<String> names);
}
