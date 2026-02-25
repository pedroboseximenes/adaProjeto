package com.example.voo.validation.voo;

import com.example.voo.domain.FiltroConsultaVoo;
import com.example.voo.exceptions.DateBeforeVooException;
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
