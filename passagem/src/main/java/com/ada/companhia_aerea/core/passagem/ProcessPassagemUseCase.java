package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.core.integrations.UserRest;
import com.ada.companhia_aerea.core.integrations.VooRest;
import com.ada.companhia_aerea.domain.PassagemCompraDTO;
import com.ada.companhia_aerea.domain.Voo;
import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.exceptions.UserNotFoundException;
import com.ada.companhia_aerea.exceptions.VooNotFoundException;
import com.ada.companhia_aerea.producers.PassagemProducer;
import com.ada.companhia_aerea.validation.user.ValidatorUser;

import java.util.List;


public class ProcessPassagemUseCase {
    private final PassagemPort repo;
    private final UserRest userRest;
    private final VooRest vooRest;    
    private final PassagemProducer passagemProducer;
    private final List<ValidatorUser> validatorUserList;

    public ProcessPassagemUseCase(PassagemPort passagemPort, UserRest userRest, VooRest vooRest,
                                  PassagemProducer passagemProducer, List<ValidatorUser> validatorUserList) {
        this.passagemProducer = passagemProducer;
        this.repo = passagemPort;
        this.userRest = userRest;
        this.validatorUserList = validatorUserList;
        this.vooRest = vooRest;
    }
    /*
     * Compra uma passagem após validar que o usuário existe.
     */
    public void execute(PassagemCompraDTO passagem, String tokenJwt){
        UserDTO user;
        user = userRest.getUserById(passagem.userId(), tokenJwt);
        try {
            validatorUserList.forEach(v -> v.validar(user));

            Voo vooEncontrado = vooRest.getUserById(passagem.vooId(), tokenJwt)
                     .filter(v -> v.assentosDisponiveis() > 0) 
                        .orElseThrow(() -> new VooNotFoundException("Voo não encontrado ou sem assentos disponíveis!"));

            JpaPassagemEntity passagemEntity = new JpaPassagemEntity(null, vooEncontrado.id(),
                    user.id(), user.name(),
                    user.email()
            );


            JpaPassagemEntity passagemSaved = repo.processarNovaPassagem(passagemEntity);
            vooRest.updateVoo(passagem.vooId(), tokenJwt);
            passagemProducer.publishMessageEmail(passagemSaved);

        } catch(UserNotFoundException e){
            throw e;
        } catch(VooNotFoundException e){
            passagemProducer.publishMessageEmailError(user);
            throw e;
        } catch(Exception e){
            passagemProducer.publishMessageEmailError(user);
            throw new RuntimeException("Erro ao processar passagem: " + e.getMessage());
        }
    }
}
