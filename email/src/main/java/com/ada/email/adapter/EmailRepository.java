package com.ada.email.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<JpaEmailEntity, Long> {
}
