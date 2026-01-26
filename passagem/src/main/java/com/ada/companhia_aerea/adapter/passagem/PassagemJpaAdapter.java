package com.ada.companhia_aerea.adapter.passagem;

import com.ada.companhia_aerea.core.passagem.PassagemPort;

import java.util.List;

public class PassagemJpaAdapter implements PassagemPort {
    private final PassagemRepository jpa;

    public PassagemJpaAdapter(PassagemRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public JpaPassagemEntity processarNovaPassagem(JpaPassagemEntity passagemEntity) {
        return jpa.save(passagemEntity);
    }

    @Override
    public List<JpaPassagemEntity> consultarPassagem(String nome_passageiro, String email_passageiro) {
        return jpa.findByNomeAndEmail(nome_passageiro, email_passageiro);
    }
}
