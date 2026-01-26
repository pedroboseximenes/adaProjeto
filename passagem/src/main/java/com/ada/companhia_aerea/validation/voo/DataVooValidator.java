package com.ada.companhia_aerea.validation.voo;

import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.exceptions.DateBeforeVooException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
public class DataVooValidator implements ValidatorVoo{
    @Override
    public void validar(FiltroConsultaVoo dados) {
        if(dados.dataIda().isEmpty()){
            return;
        }
        LocalDate date = LocalDate.parse(dados.dataIda(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(date.isBefore(LocalDate.now())) {
            throw new DateBeforeVooException("Data do voo nao pode ser anterior a data atual.");
        }

    }
}
