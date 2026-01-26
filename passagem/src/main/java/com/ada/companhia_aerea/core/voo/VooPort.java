package com.ada.companhia_aerea.core.voo;

import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VooPort {
    public List<JpaVooEntity> consultVoo(FiltroConsultaVoo filtro);
    public void updateVoo(Long vooId);
    public Optional<JpaVooEntity> consultById(Long vooId);
}
