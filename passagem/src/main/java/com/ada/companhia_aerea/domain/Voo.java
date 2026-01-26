package com.ada.companhia_aerea.domain;

import java.math.BigDecimal;

public record Voo(Long id, String codigo, String origem, String destino, String dataHora, BigDecimal preco, Integer assentosTotais, Integer assentosDisponiveis ) {
}
