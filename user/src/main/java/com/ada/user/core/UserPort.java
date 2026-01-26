package com.ada.user.core;

import com.ada.user.domain.UserDTO;
import com.ada.user.adapter.JpaUserEntity;

import java.util.Optional;

public interface UserPort {

    public UserDTO register(UserDTO user);

    public Optional<JpaUserEntity> login(UserDTO user);

    public Optional<UserDTO> getUserById(Long id);
}
