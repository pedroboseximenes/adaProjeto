package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.domain.Passagem;
import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.validation.user.ValidatorUser;

import java.util.List;

public class GetPassagemByNameEmailUseCase {
    private final PassagemPort repo;
    private final List<ValidatorUser> validatorUserList;

    public GetPassagemByNameEmailUseCase(PassagemPort passagemPort, List<ValidatorUser> validatorUserList) {
        this.repo = passagemPort;
        this.validatorUserList = validatorUserList;
    }
    /*
     * Consulta as passagens de um usario.
     */
    public List<Passagem> execute(UserDTO userDTO){
        validatorUserList.forEach(v -> v.validar(userDTO));

        List<JpaPassagemEntity> passagemEntites = repo.consultarPassagem(userDTO.name(), userDTO.email());
        return passagemEntites.stream().map(p -> new Passagem(p.getId(),
                p.getVoo(), p.getUser_id(),
                p.getNome(), p.getEmail(),
                p.getData_compra()
               )).toList();
    }
}
