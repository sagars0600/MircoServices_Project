package com.mircoservices.account.exceptions;

public class CustomerNotActiveException extends RuntimeException {
    public CustomerNotActiveException(String message) {
        super(message);
    }
}
