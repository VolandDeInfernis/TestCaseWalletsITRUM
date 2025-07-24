package org.example.wallet.exception;

public class InvalidRequest extends RuntimeException {
    public InvalidRequest(String message) {
        super("Invalid request: " + message);
    }
}