package com.ada.companhia_aerea.core.voo;

import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;

import java.util.List;

public class GetVooUseCase {
    private final VooPort repo;
    public GetVooUseCase(VooPort vooPort) {
        this.repo = vooPort;
    }
    /*
     * Consulta os voos com base no filtro fornecido e mapeia as entidades JPA para objetos de domínio Voo.
     */
    public List<Voo> execute(FiltroConsultaVoo filtro){
        /*
            Validar o filtro
         */
        List<JpaVooEntity> voosEntites = repo.consultVoo(filtro);
        return voosEntites.stream().map(v -> new Voo(v.getId(),
                    v.getCodigo(), v.getOrigem(),
                v.getDestino(), v.getData_hora(),
                v.getPreco(), v.getAssentos_totais(),
                v.getAssentos_disponiveis()))
                .toList();
    }
}
