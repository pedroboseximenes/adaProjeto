package com.ada.user.adapter;

import com.ada.user.core.UserPort;
import com.ada.user.domain.User;

import java.util.Optional;

public class UserJpaAdapter implements UserPort {
    private final UserRepository jpa;

    public UserJpaAdapter(UserRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public User register(User user) {
        JpaUserEntity jpaUserEntity = new JpaUserEntity(
                user.name(),
                user.email(),
                user.password()
        );
        JpaUserEntity userSaved = jpa.save(jpaUserEntity);
        return new User(
                userSaved.getId(),
                userSaved.getName(),
                userSaved.getEmail(),
                userSaved.getPassword()
        );
    }

    @Override
    public Optional<JpaUserEntity> login(User user) {
        return jpa.findByEmail(user.email());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<JpaUserEntity> u = jpa.findById(id);

        return u.map(user -> new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        ));
    }
}
