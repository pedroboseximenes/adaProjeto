package com.ada.email.validators;

import com.ada.email.domain.EmailDTO;

public interface Validator {
    void validar(EmailDTO dados);
}
