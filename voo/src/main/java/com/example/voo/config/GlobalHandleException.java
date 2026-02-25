package com.example.voo.config;

import com.example.voo.exceptions.DateBeforeVooException;
import com.example.voo.exceptions.VooNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler({
            IllegalArgumentException.class,
            RuntimeException.class
    })
    public ResponseEntity<String> handleArgumentException(RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

}
