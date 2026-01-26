package com.ada.user.core;

import com.ada.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUseCase{
    private final UserPort repo;
    private final PasswordEncoder passwordEncoder;


    public RegisterUseCase(UserPort repo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;

    }

    public User execute(User user) {
        User userEncriptado = new User(
                null,
                user.name(),
                user.email(),
                passwordEncoder.encode(user.password())
        );
        return repo.register(userEncriptado);
    }
}
