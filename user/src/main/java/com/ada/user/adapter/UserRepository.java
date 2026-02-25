package com.ada.user.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<JpaUserEntity, Long> {
    Optional<JpaUserEntity> findByEmail(String email);
}
