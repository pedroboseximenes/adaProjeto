package com.ada.companhia_aerea.validation.user;

import com.ada.companhia_aerea.domain.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        if(user != null && user.name().isEmpty()){
            throw new IllegalArgumentException("Nome deve estar preenchido.");
        }
    }
}
