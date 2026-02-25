package com.example.voo.validation.voo;

import com.example.voo.domain.FiltroConsultaVoo;
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
