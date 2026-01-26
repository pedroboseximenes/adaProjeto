package com.ada.companhia_aerea.exceptions;

public class DateBeforeVooException extends RuntimeException {
    public DateBeforeVooException(String message) {
        super(message);
    }
}
