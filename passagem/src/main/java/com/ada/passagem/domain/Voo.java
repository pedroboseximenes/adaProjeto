package com.ada.passagem.domain;

public record Voo( Long id, String codigo, String origem, String destino, String dataHora, Double preco, Integer assentosTotais, Integer assentosDisponiveis ) {
}
