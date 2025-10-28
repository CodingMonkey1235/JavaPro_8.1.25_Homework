package org.example.exception;

public class PaymentExecuteException extends RuntimeException {
    public PaymentExecuteException(String message) {
        super(message);
    }
}
