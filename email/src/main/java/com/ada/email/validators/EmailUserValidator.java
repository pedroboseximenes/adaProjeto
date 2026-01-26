package com.ada.email.validators;

import com.ada.email.domain.EmailDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailUserValidator implements Validator{
    @Override
    public void validar(EmailDTO dados) {
        System.out.println("Validando o campo userID...");
        if(dados.getUserId() == null){
            throw new IllegalArgumentException("ID usuario nao pode ser nulo.");
        }
    }
}
