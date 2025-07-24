package org.example.wallet.exception;

import java.util.UUID;

public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(UUID walletId) {
        super("Insufficient funds in wallet: " + walletId);
    }
}