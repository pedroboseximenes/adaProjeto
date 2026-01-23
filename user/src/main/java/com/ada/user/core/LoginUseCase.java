package com.ada.user.core;

import com.ada.user.domain.User;
import com.ada.user.adapter.JpaUserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginUseCase {
    private final UserUseCase repo;

    private final AuthenticationManager authenticationManager;

    public LoginUseCase(UserUseCase repo,  AuthenticationManager authenticationManager) {
        this.repo = repo;

        this.authenticationManager = authenticationManager;
    }

    public JpaUserEntity execute(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                )
        );

            return repo.login(user).orElseThrow(() -> new RuntimeException("Invalid credentials"));

    }
}
