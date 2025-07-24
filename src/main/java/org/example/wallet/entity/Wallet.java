package org.example.wallet.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private Long balance = 0L;

    @Version
    private Long version;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public Long getBalance() {
        return this.balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}