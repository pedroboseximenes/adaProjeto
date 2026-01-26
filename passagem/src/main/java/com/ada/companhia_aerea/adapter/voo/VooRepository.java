package com.ada.companhia_aerea.adapter.voo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VooRepository extends JpaRepository<JpaVooEntity, Long> {
    List<JpaVooEntity> findByOrigemAndDestinoAndDataHoraBetween(String origem,
                                                                          String destino,
                                                                          LocalDateTime dataInicio,
                                                                          LocalDateTime dataFim);
    List<JpaVooEntity> findByOrigemAndDestino(String origem,
                                                                String destino);
}
