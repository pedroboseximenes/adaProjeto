package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.core.integrations.UserRest;
import com.ada.companhia_aerea.core.voo.GetVooByIdUseCase;
import com.ada.companhia_aerea.core.voo.UpdateVooUseCase;
import com.ada.companhia_aerea.core.voo.VooPort;
import com.ada.companhia_aerea.domain.PassagemCompraDTO;
import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.exceptions.UserNotFoundException;
import com.ada.companhia_aerea.exceptions.VooNotFoundException;
import com.ada.companhia_aerea.producers.PassagemProducer;
import com.ada.companhia_aerea.validation.user.ValidatorUser;

import java.util.List;


public class ProcessPassagemUseCase {
    private final PassagemPort repo;
    private final UpdateVooUseCase updateVooUseCase;
    private final UserRest userRest;
    private final PassagemProducer passagemProducer;
    private final GetVooByIdUseCase getVooByIdUseCase;
    private final List<ValidatorUser> validatorUserList;

    public ProcessPassagemUseCase(PassagemPort passagemPort, UserRest userRest,
                                  GetVooByIdUseCase getVooByIdUseCase,
                                  UpdateVooUseCase updateVooUseCase, PassagemProducer passagemProducer, List<ValidatorUser> validatorUserList) {
        this.passagemProducer = passagemProducer;
        this.repo = passagemPort;
        this.userRest = userRest;
        this.updateVooUseCase = updateVooUseCase;
        this.getVooByIdUseCase = getVooByIdUseCase;
        this.validatorUserList = validatorUserList;
    }
    /*
     * Compra uma passagem após validar que o usuário existe.
     */
    public void execute(PassagemCompraDTO passagem, String tokenJwt){
        UserDTO user;
        user = userRest.getUserById(passagem.userId(), tokenJwt);
        try {
            validatorUserList.forEach(v -> v.validar(user));

            JpaVooEntity vooEncontrado = getVooByIdUseCase.execute(passagem.vooId())
                    .map(v -> v.getAssentos_disponiveis() > 0 ? v : null)
                    .orElseThrow(() -> new VooNotFoundException("Voo não encontrado!"));

            JpaPassagemEntity passagemEntity = new JpaPassagemEntity(null, vooEncontrado,
                    user.id(), user.name(),
                    user.email()
            );


            JpaPassagemEntity passagemSaved = repo.processarNovaPassagem(passagemEntity);
            updateVooUseCase.execute(passagem.vooId());
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
