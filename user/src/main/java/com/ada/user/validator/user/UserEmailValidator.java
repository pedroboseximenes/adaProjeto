package com.ada.user.validator.user;


import com.ada.user.domain.UserDTO;

public class UserEmailValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        if(user.email().isEmpty()){
            throw new IllegalArgumentException("Email nao pode ser nulo.");
        }
    }
}
