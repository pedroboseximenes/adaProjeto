package com.ada.companhia_aerea.adapter.voo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VooRepository extends JpaRepository<JpaVooEntity, Long> {
    Optional<List<JpaVooEntity>> findByOrigemAndDestino(String origem, String destino);
}
