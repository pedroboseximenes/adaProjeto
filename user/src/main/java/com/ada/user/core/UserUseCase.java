package com.ada.user.core;

import com.ada.user.domain.User;
import com.ada.user.fronteira.JpaUserEntity;

import java.util.Optional;

public interface UserUseCase {

    public User register(User user);

    public Optional<JpaUserEntity> login(User user);
}
