package com.ada.email.validators;

import com.ada.email.domain.EmailDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailToValidator implements Validator {
    @Override
    public void validar(EmailDTO dados) {
        System.out.println("Validando o campo emailTo...");
        if(dados.getEmailTo().isEmpty() || dados.getEmailTo().isBlank()){
            throw new IllegalArgumentException("O campo emailTo nao pode estar vazio ou em branco.");
        }
    }
}