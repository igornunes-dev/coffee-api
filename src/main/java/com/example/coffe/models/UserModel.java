package com.example.coffe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "password is mandatory")
    @Column(nullable = false)
    private String password;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_id", cascade = CascadeType.ALL)
    private Set<PurchaseModel> purchases = new HashSet<>();

    public UserModel() {

    }
    public UserModel(String name, String email, String password, BigDecimal balance, Set<PurchaseModel> purchases) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.purchases = purchases;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PurchaseModel> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseModel> purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
