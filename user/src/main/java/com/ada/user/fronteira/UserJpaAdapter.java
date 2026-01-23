package com.ada.user.fronteira;

import com.ada.user.core.UserUseCase;
import com.ada.user.domain.User;

import java.util.Optional;

public class UserJpaAdapter implements UserUseCase {
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
}
