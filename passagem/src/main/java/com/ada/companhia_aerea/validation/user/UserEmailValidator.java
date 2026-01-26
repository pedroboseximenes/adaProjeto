package com.ada.companhia_aerea.validation.user;

import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        if( user != null && user.email().isEmpty()){
            throw new IllegalArgumentException("Email deve estar preenchido.");
        }
    }
}
