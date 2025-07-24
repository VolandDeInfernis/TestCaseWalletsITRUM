package org.example.wallet.controller;

import org.example.wallet.exception.InsufficientFunds;
import org.example.wallet.exception.WalletNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(WalletNotFound.class)
    public ResponseEntity<String> handleNotFound(WalletNotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Wallet not found: " + ex.getMessage());
    }

    @ExceptionHandler(InsufficientFunds.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFunds ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Insufficient funds: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid UUID format");
    }
}