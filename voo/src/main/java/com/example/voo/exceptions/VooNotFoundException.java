package com.example.voo.exceptions;

public class VooNotFoundException extends RuntimeException {
    public VooNotFoundException(String message) {
        super(message);
    }
}
