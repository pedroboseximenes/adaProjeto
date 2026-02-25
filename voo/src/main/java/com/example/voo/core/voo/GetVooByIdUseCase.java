package com.example.voo.core.voo;

import com.example.voo.adapter.voo.JpaVooEntity;
import com.example.voo.domain.FiltroConsultaVoo;
import com.example.voo.domain.Voo;
import com.example.voo.exceptions.VooNotFoundException;

import java.util.List;
import java.util.Optional;

public class GetVooByIdUseCase {
    private final VooPort repo;
    public GetVooByIdUseCase(VooPort vooPort) {
        this.repo = vooPort;
    }
    /*
     * Consulta os voos com base no filtro fornecido e mapeia as entidades JPA para objetos de domínio Voo.
     */
    public Voo execute(Long vooId){
        return repo.consultById(vooId)
            .map(entity -> new Voo(
                entity.getId(),
                entity.getCodigo(),
                entity.getOrigem(),
                entity.getDestino(),
                entity.getDataHora(),
                entity.getPreco(),
                entity.getAssentos_totais(),
                entity.getAssentos_disponiveis()
            ))
            .orElseThrow(() -> new VooNotFoundException("Voo não encontrado"));
    }
}
