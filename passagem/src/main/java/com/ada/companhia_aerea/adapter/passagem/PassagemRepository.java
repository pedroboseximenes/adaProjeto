package com.ada.companhia_aerea.adapter.passagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassagemRepository  extends JpaRepository<JpaPassagemEntity, Long> {
    public List<JpaPassagemEntity> findByNomeAndEmail(String nome_passageiro, String email_passageiro);
}
