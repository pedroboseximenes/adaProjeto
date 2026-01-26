package com.ada.companhia_aerea.validation.user;

import com.ada.companhia_aerea.domain.Passagem;
import com.ada.companhia_aerea.domain.UserDTO;

public interface ValidatorUser {
    public void validar(UserDTO user);
}
