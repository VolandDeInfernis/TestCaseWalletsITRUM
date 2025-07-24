package org.example.wallet.service;

import org.example.wallet.dto.WalletOperationRequest;
import org.example.wallet.dto.WalletBalanceResponse;
import org.example.wallet.entity.OperationType;
import org.example.wallet.entity.Wallet;
import org.example.wallet.entity.Transaction;
import org.example.wallet.exception.WalletNotFound;
import org.example.wallet.exception.InsufficientFunds;
import org.example.wallet.repository.WalletRepository;
import org.example.wallet.repository.TransactionRepository;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository,
                         TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Retryable(maxAttempts = 3)
    @Transactional
    public void processOperation(WalletOperationRequest request) {
        Wallet wallet = walletRepository.findByIdForUpdate(request.walletId())
                .orElseThrow(() -> new WalletNotFound(request.walletId()));

        if (request.operationType() == OperationType.WITHDRAW &&
                wallet.getBalance() < request.amount()) {
            throw new InsufficientFunds(request.walletId());
        }

        long newBalance = request.operationType() == OperationType.DEPOSIT
                ? wallet.getBalance() + request.amount()
                : wallet.getBalance() - request.amount();

        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setOperationType(request.operationType());
        transaction.setAmount(request.amount());
        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public WalletBalanceResponse getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFound(walletId));
        return new WalletBalanceResponse(walletId, wallet.getBalance());
    }
}