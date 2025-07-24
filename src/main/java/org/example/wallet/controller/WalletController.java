package org.example.wallet.controller;

import org.example.wallet.dto.WalletOperationRequest;
import org.example.wallet.dto.WalletBalanceResponse;
import org.example.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Void> processOperation(
            @RequestBody @Valid WalletOperationRequest request) {
        walletService.processOperation(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletBalanceResponse> getBalance(
            @PathVariable UUID walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }
}