package com.ada.companhia_aerea.core.voo;

import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;

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
    public Optional<JpaVooEntity> execute(Long vooId){
         return repo.consultById(vooId);
    }
}
