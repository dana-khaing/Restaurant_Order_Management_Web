package com.oaxaca.customer_service.exception;

public class CustomerCreationFailedException extends RuntimeException{

    public CustomerCreationFailedException(String message) {
        super(message);
    }
}
