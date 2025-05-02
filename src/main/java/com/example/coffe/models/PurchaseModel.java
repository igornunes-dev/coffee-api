package com.example.coffe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PURCHASE")
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Positive
    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalTime date_purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchase_coffee",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id")
    )
    private Set<CoffeeModel> coffees = new HashSet<>();

    public PurchaseModel() {

    }

    public PurchaseModel(Integer quantity, LocalTime date_purchase, UserModel user_id, Set<CoffeeModel> coffees) {
        this.quantity = quantity;
        this.date_purchase = date_purchase;
        this.user_id = user_id;
        this.coffees = coffees;
    }

    public UUID getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalTime getDate_purchase() {
        return date_purchase;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate_purchase(LocalTime date_purchase) {
        this.date_purchase = date_purchase;
    }

    public UserModel getUser_id() {
        return user_id;
    }

    public void setUser_id(UserModel user_id) {
        this.user_id = user_id;
    }

    public Set<CoffeeModel> getCoffees() {
        return coffees;
    }

    public void setCoffees(Set<CoffeeModel> coffees) {
        this.coffees = coffees;
    }


}
