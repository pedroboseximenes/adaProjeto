package com.ada.passagem.controller;

import com.ada.passagem.domain.FiltroPassagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/voos")
@RestController
public class PassagemController {
    @GetMapping("/consultar-por-filtro")
    public ResponseEntity<String> consultarPorFiltro(@RequestBody FiltroPassagem filtroPassagem) {


        return ResponseEntity.ok("TESTE");
    }
}
