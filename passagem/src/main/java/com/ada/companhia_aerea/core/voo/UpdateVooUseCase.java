package com.ada.companhia_aerea.core.voo;

import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;

import java.util.List;

public class UpdateVooUseCase {
    private final VooPort repo;
    public UpdateVooUseCase(VooPort vooPort) {
        this.repo = vooPort;
    }
    /*
     * Atualiza voo os assentos totais com base no processamento da Passagem.
     */
    public void execute(Long vooId){
        repo.updateVoo(vooId);
    }
}
