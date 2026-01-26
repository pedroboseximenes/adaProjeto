package com.ada.email.validators;

import com.ada.email.domain.EmailDTO;

public interface Validator {
    public void validar(EmailDTO dados);
}
