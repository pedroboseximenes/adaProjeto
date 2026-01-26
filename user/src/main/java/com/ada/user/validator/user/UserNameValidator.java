package com.ada.user.validator.user;


import com.ada.user.domain.UserDTO;

public class UserNameValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        if(user.name().isEmpty()){
            throw new IllegalArgumentException("Nome nao pode ser nulo.");
        }
    }
}
