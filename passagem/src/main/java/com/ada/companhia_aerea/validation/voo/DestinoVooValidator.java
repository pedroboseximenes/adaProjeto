package com.ada.companhia_aerea.validation.voo;

import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;
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
