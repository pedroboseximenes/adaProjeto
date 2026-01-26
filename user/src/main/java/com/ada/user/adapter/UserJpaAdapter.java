package com.ada.user.adapter;

import com.ada.user.core.UserPort;
import com.ada.user.domain.UserDTO;

import java.util.Optional;

public class UserJpaAdapter implements UserPort {
    private final UserRepository jpa;

    public UserJpaAdapter(UserRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public UserDTO register(UserDTO user) {
        JpaUserEntity jpaUserEntity = new JpaUserEntity(
                user.name(),
                user.email(),
                user.password()
        );
        JpaUserEntity userSaved = jpa.save(jpaUserEntity);
        return new UserDTO(
                userSaved.getId(),
                userSaved.getName(),
                userSaved.getEmail(),
                userSaved.getPassword()
        );
    }

    @Override
    public Optional<JpaUserEntity> login(UserDTO user) {
        return jpa.findByEmail(user.email());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<JpaUserEntity> u = jpa.findById(id);

        return u.map(user -> new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        ));
    }
}
