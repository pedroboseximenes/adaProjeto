package com.ada.companhia_aerea.core.voo;

import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;
import com.ada.companhia_aerea.validation.voo.ValidatorVoo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GetVooUseCase {
    private final VooPort repo;
    private final List<ValidatorVoo> validatorVoos;
    public GetVooUseCase(VooPort vooPort, List<ValidatorVoo> validatorVoos) {
        this.repo = vooPort;
        this.validatorVoos = validatorVoos;
    }
    /*
     * Consulta os voos com base no filtro fornecido e mapeia as entidades JPA para objetos de domínio Voo.
     */
    public List<Voo> execute(FiltroConsultaVoo filtro){
        validatorVoos.forEach(v-> v.validar(filtro));


        List<JpaVooEntity> voosEntites = repo.consultVoo(filtro);
        return voosEntites.stream().map(v -> new Voo(v.getId(),
                    v.getCodigo(), v.getOrigem(),
                v.getDestino(), v.getDataHora(),
                v.getPreco(), v.getAssentos_totais(),
                v.getAssentos_disponiveis()))
                .toList();
    }
}
