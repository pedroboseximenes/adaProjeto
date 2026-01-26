package com.ada.user.core;

import com.ada.user.domain.User;
import com.ada.user.adapter.JpaUserEntity;

import java.util.Optional;

public interface UserPort {

    public User register(User user);

    public Optional<JpaUserEntity> login(User user);

    public Optional<User> getUserById(Long id);
}
