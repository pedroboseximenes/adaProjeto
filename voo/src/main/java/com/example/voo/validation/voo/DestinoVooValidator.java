package com.example.voo.validation.voo;

import com.example.voo.domain.FiltroConsultaVoo;
import com.example.voo.domain.Voo;
import org.springframework.stereotype.Component;

@Component
public class DestinoVooValidator implements ValidatorVoo{
    @Override
    public void validar(FiltroConsultaVoo dados) {
        if(dados.destino().isEmpty()){
            throw new IllegalArgumentException("Destino do voo nao pode ser vazio.");
        }

    }
}
