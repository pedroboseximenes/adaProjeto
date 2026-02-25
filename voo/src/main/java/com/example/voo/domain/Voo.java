package com.example.voo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Voo(Long id, String codigo, String origem, String destino, LocalDateTime dataHora, BigDecimal preco, Integer assentosTotais, Integer assentosDisponiveis ) {
}
