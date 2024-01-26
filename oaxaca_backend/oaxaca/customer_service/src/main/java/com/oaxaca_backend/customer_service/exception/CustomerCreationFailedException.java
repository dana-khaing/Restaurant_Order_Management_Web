package com.oaxaca_backend.customer_service.exception;

public class CustomerCreationFailedException extends RuntimeException{

    public CustomerCreationFailedException(String message) {
        super(message);
    }
}
