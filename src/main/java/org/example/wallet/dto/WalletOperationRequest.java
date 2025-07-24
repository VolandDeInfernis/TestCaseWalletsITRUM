package org.example.wallet.dto;

import org.example.wallet.entity.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record WalletOperationRequest(
        @NotNull(message = "Wallet ID cannot be null")
        UUID walletId,

        @NotNull(message = "Operation type cannot be null")
        OperationType operationType,

        @Positive(message = "Amount must be positive")
        @NotNull(message = "Amount cannot be null")
        Long amount
) {}