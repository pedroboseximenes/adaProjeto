package com.ada.companhia_aerea.exceptions;

public class VooNotFoundException extends RuntimeException {
    public VooNotFoundException(String message) {
        super(message);
    }
}
