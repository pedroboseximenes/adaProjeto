package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;

import java.util.List;

public interface PassagemPort {
    public JpaPassagemEntity processarNovaPassagem(JpaPassagemEntity passagemEntity);
    public List<JpaPassagemEntity> consultarPassagem(String nome_passageiro, String email_passageiro);
}
