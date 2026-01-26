package com.ada.user.core;

import com.ada.user.domain.User;

public class FindByIdUserUseCase {
    private final UserPort repo;


    public FindByIdUserUseCase(UserPort repo) {
        this.repo = repo;

    }

    public User execute(Long id) {
        return repo.getUserById(id).orElse(null);
    }
}
