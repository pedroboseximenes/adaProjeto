package com.ada.email.core;

import com.ada.email.domain.EmailDTO;
import com.ada.email.validators.Validator;

import java.util.List;

public class SendEmailUseCase {
    private final EmailPort repo;
    private final List<Validator> validatorEmail;

    public SendEmailUseCase(EmailPort repo, List<Validator> validatorEmail) {
        this.repo = repo;
        this.validatorEmail = validatorEmail;
    }
    public void execute(EmailDTO email) {
        validatorEmail.forEach(v -> v.validar(email));
        repo.enviarEmail(email);
    }
}
