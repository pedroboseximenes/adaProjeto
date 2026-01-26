package com.ada.companhia_aerea.config;

import com.ada.companhia_aerea.exceptions.DateBeforeVooException;
import com.ada.companhia_aerea.exceptions.UserNotFoundException;
import com.ada.companhia_aerea.exceptions.VooNotFoundException;
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
