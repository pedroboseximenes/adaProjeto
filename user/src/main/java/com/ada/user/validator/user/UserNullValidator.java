package com.ada.user.validator.user;


import com.ada.user.domain.UserDTO;
import com.ada.user.exceptions.UserNotFoundException;

public class UserNullValidator implements  ValidatorUser{
    @Override
    public void validar(UserDTO user) {
        if(user == null){
            throw new UserNotFoundException("Usuario nao pode ser nulo.");
        }
    }
}
