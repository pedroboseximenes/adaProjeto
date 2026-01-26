package com.ada.companhia_aerea.validation.voo;

import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import org.springframework.stereotype.Component;

@Component
public class OrigemVooValidator implements ValidatorVoo{
    @Override
    public void validar(FiltroConsultaVoo dados) {
        if(dados.origem().isEmpty()){
            throw new IllegalArgumentException("Origem do voo nao pode ser vazio.");
        }

    }
}
