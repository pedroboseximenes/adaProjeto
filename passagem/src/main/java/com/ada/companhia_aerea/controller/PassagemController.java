package com.ada.companhia_aerea.controller;

import com.ada.companhia_aerea.core.passagem.GetPassagemByNameEmailUseCase;
import com.ada.companhia_aerea.core.passagem.ProcessPassagemUseCase;
import com.ada.companhia_aerea.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passagem")

public class PassagemController {
    private final ProcessPassagemUseCase processPassagemUseCase;
    private final GetPassagemByNameEmailUseCase getPassagemByNameEmailUseCase;

    public PassagemController(ProcessPassagemUseCase processPassagemUseCase, GetPassagemByNameEmailUseCase getPassagemByNameEmailUseCase) {
        this.processPassagemUseCase = processPassagemUseCase;
        this.getPassagemByNameEmailUseCase = getPassagemByNameEmailUseCase;
    }
    @GetMapping
    public List<Passagem> consultarPorUsuario(@RequestBody UserDTO userDTO) {

        List<Passagem> passagens = getPassagemByNameEmailUseCase.execute(userDTO);
        return passagens;
    }
    @PostMapping
    public ResponseEntity<String> buyPassagem(@RequestBody PassagemCompraDTO passagemCompraDTO,
                                                @RequestHeader("Authorization") String tokenJwt) {
        processPassagemUseCase.execute(passagemCompraDTO, tokenJwt);
        return ResponseEntity.ok("Requisição em processamento. Confira seu email.");
    }
}
