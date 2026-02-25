package com.example.voo.core.voo;

import com.example.voo.adapter.voo.JpaVooEntity;
import com.example.voo.domain.FiltroConsultaVoo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VooPort {
    public List<JpaVooEntity> consultVoo(FiltroConsultaVoo filtro);
    public void updateVoo(Long vooId);
    public Optional<JpaVooEntity> consultById(Long vooId);
}
