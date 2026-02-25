package com.example.voo.core.voo;

import com.example.voo.adapter.voo.JpaVooEntity;
import com.example.voo.domain.FiltroConsultaVoo;
import com.example.voo.domain.Voo;

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
