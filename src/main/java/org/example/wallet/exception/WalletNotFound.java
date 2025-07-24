package org.example.wallet.exception;

import java.util.UUID;

public class WalletNotFound extends RuntimeException {
    public WalletNotFound(UUID walletId) {
        super("Wallet not found with ID: " + walletId);
    }
}