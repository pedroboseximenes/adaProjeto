package com.ada.companhia_aerea.validation.user;

import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserNullValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        System.out.println("Validando se o usuario e nulo...");
        if(user == null){
            throw new UserNotFoundException("Usuario nao encontrado. Tente novamente.");
        }
    }
}
