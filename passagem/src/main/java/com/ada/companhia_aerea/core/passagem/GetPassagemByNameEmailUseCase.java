package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.PassagemApplication;
import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.core.voo.VooPort;
import com.ada.companhia_aerea.domain.FiltroConsultaVoo;
import com.ada.companhia_aerea.domain.Passagem;
import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.domain.Voo;

import java.util.List;

public class GetPassagemByNameEmailUseCase {
    private final PassagemPort repo;
    public GetPassagemByNameEmailUseCase(PassagemPort passagemPort) {
        this.repo = passagemPort;
    }
    /*
     * Consulta as passagens de um usario.
     */
    public List<Passagem> execute(UserDTO userDTO){
        /*
            Validar o filtro
         */
        List<JpaPassagemEntity> passagemEntites = repo.consultarPassagem(userDTO.name(), userDTO.email());
        return passagemEntites.stream().map(p -> new Passagem(p.getId(),
                p.getVoo().getId(), p.getUser_id(),
                p.getNome_passageiro(), p.getEmail_passageiro(),
                p.getData_compra()
               )).toList();
    }
}
