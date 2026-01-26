package com.ada.companhia_aerea.controller;

import com.ada.companhia_aerea.core.voo.GetVooUseCase;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Voo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voo")
public class VooController {
    private final GetVooUseCase getVooUseCase;

    public VooController(GetVooUseCase getVooUseCase) {
        this.getVooUseCase = getVooUseCase;
    }

    @GetMapping
    public List<Voo> consultarPorFiltro(@RequestBody FiltroConsultaVoo filtroConsultaVoo) {

        List<Voo> voos = getVooUseCase.execute(filtroConsultaVoo);
        return voos;
    }
}
