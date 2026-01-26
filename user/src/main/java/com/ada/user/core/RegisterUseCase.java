package com.ada.user.core;

import com.ada.user.domain.UserDTO;
import com.ada.user.validator.user.ValidatorUser;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class RegisterUseCase{
    private final UserPort repo;
    private final PasswordEncoder passwordEncoder;
    private final List<ValidatorUser> validatorUserList;


    public RegisterUseCase(UserPort repo, PasswordEncoder passwordEncoder, List<ValidatorUser> validatorUserList) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
        this.validatorUserList = validatorUserList;
    }

    public UserDTO execute(UserDTO user) {
        validatorUserList.forEach(v -> v.validar(user));

        UserDTO userEncriptado = new UserDTO(
                null,
                user.name(),
                user.email(),
                passwordEncoder.encode(user.password())
        );
        return repo.register(userEncriptado);
    }
}
