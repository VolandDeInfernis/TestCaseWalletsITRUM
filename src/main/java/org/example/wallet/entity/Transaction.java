package org.example.wallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(nullable = false)
    private Long amount;

    @Column(updatable = false, nullable = false)
    private Instant createdAt = Instant.now();

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setOperationType(@NotNull(message = "Operation type cannot be null") OperationType operationType) {
        this.operationType = operationType;
    }

    public void setAmount(@Positive(message = "Amount must be positive") @NotNull(message = "Amount cannot be null") Long amount) {
        this.amount = amount;
    }
}