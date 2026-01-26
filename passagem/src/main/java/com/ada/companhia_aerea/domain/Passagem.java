package com.ada.companhia_aerea.domain;

import java.time.LocalDateTime;

public record Passagem(Long id, Long vooId, Long userId, String nomePassageiro, String emailPassageiro, LocalDateTime dataCompra) {
}


