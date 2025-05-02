package com.example.coffe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_COFFE")
public class CoffeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Version
    private Long version;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "description is mandatory")
    @Column(nullable = false)
    private String description;

    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @ManyToMany(mappedBy = "coffees")
    private Set<PurchaseModel> purchase = new HashSet<>();

    public CoffeeModel() {

    }

    public CoffeeModel(String name, String description, BigDecimal price, Integer stock, Set<PurchaseModel> purchase) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.purchase = purchase;
    }



    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

