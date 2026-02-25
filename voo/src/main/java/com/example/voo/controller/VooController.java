package com.example.voo.controller;

import com.example.voo.core.voo.GetVooUseCase;
import com.example.voo.core.voo.GetVooByIdUseCase;
import com.example.voo.core.voo.UpdateVooUseCase;
import com.example.voo.domain.FiltroConsultaVoo;
import com.example.voo.domain.Voo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/voo")
public class VooController {
    private final GetVooUseCase getVooUseCase;
    private final GetVooByIdUseCase getVooByIdUseCase;
    private final UpdateVooUseCase updateVooUseCase;

    public VooController(GetVooUseCase getVooUseCase, GetVooByIdUseCase getVooByIdUseCase, UpdateVooUseCase updateVooUseCase) {
        this.getVooUseCase = getVooUseCase;
        this.getVooByIdUseCase = getVooByIdUseCase;
        this.updateVooUseCase = updateVooUseCase;
    }

    @GetMapping
    public List<Voo> consultarPorFiltro(@RequestBody FiltroConsultaVoo filtroConsultaVoo) {

        List<Voo> voos = getVooUseCase.execute(filtroConsultaVoo);
        return voos;
    }

    @GetMapping("/{vooId}")
    public Voo consultaPorId(@PathVariable Long vooId) {

        return getVooByIdUseCase.execute(vooId);        
    }

    @PostMapping("/{vooId}")
    public void atualizar(@PathVariable Long vooId){
        updateVooUseCase.execute(vooId);
    }
}
