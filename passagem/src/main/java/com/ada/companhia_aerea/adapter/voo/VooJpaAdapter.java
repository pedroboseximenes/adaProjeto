package com.ada.companhia_aerea.adapter.voo;

import com.ada.companhia_aerea.core.voo.VooPort;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;

import java.util.List;
import java.util.Optional;

public class VooJpaAdapter implements VooPort {
    private final VooRepository jpa;

    public VooJpaAdapter(VooRepository jpa) {
        this.jpa = jpa;
    }
    @Override
    public List<JpaVooEntity> consultVoo(FiltroConsultaVoo filtro) {
        return jpa.findByOrigemAndDestino(filtro.origem(), filtro.destino()).orElse(null);
    }

    @Override
    public void updateVoo(Long vooId) {
        JpaVooEntity vooEntity = jpa.findById(vooId).orElseThrow();
        Integer assentosDisponiveis = vooEntity.getAssentos_disponiveis();
        vooEntity.setAssentos_disponiveis(assentosDisponiveis - 1);
        jpa.save(vooEntity);

    }

    @Override
    public Optional<JpaVooEntity> consultById(Long vooId) {
        return jpa.findById(vooId);
    }
}
