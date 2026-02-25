package com.example.voo.adapter.voo;

import com.example.voo.core.voo.VooPort;
import com.example.voo.domain.FiltroConsultaVoo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class VooJpaAdapter implements VooPort {
    private final VooRepository jpa;

    public VooJpaAdapter(VooRepository jpa) {
        this.jpa = jpa;
    }
    @Override
    public List<JpaVooEntity> consultVoo(FiltroConsultaVoo filtro) {
        if(verificarDataEstaPreenchida(filtro.dataIda())) {
            return jpa.findByOrigemAndDestino(filtro.origem(), filtro.destino());
        }

        LocalDate date = LocalDate.parse(filtro.dataIda(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDateTime inicioDia = date.atStartOfDay();
        LocalDateTime fimDia = date.atTime(LocalTime.MAX);

        return jpa.findByOrigemAndDestinoAndDataHoraBetween(filtro.origem(), filtro.destino(), inicioDia, fimDia);
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

    private boolean verificarDataEstaPreenchida(String dataIda){
        return dataIda == null || dataIda.isEmpty();
    }
}
