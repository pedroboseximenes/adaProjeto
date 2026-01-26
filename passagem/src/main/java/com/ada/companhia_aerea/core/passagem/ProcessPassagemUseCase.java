package com.ada.companhia_aerea.core.passagem;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import com.ada.companhia_aerea.core.integrations.UserRest;
import com.ada.companhia_aerea.core.voo.GetVooByIdUseCase;
import com.ada.companhia_aerea.core.voo.UpdateVooUseCase;
import com.ada.companhia_aerea.core.voo.VooPort;
import com.ada.companhia_aerea.domain.PassagemCompraDTO;
import com.ada.companhia_aerea.domain.UserDTO;
import com.ada.companhia_aerea.producers.PassagemProducer;


public class ProcessPassagemUseCase {
    private final PassagemPort repo;
    private final UpdateVooUseCase updateVooUseCase;
    private final UserRest userRest;
    private final PassagemProducer passagemProducer;
    private final GetVooByIdUseCase getVooByIdUseCase;

    public ProcessPassagemUseCase(PassagemPort passagemPort, UserRest userRest,
                                  GetVooByIdUseCase getVooByIdUseCase,
                                  UpdateVooUseCase updateVooUseCase, PassagemProducer passagemProducer) {
        this.passagemProducer = passagemProducer;
        this.repo = passagemPort;
        this.userRest = userRest;
        this.updateVooUseCase = updateVooUseCase;
        this.getVooByIdUseCase = getVooByIdUseCase;
    }
    /*
     * Compra uma passagem após validar que o usuário existe.
     */
    public void execute(PassagemCompraDTO passagem){
        //Processar Melhor
        Long userId = passagem.userId();
        UserDTO user = userRest.getUserById(userId);
        System.out.println("Usuario encontrado: " + user);

        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado: " + userId);
        }

        JpaVooEntity vooEncontrado = getVooByIdUseCase.execute(passagem.vooId())
                .orElseThrow(() -> new RuntimeException("Voo não encontrado!"));


        JpaPassagemEntity passagemEntity = new JpaPassagemEntity(null, vooEncontrado,
                user.id(), user.name(),
                user.email()
        );
        JpaPassagemEntity passagemSaved = repo.processarNovaPassagem(passagemEntity);
        updateVooUseCase.execute(passagem.vooId());
        passagemProducer.publishMessageEmail(passagemSaved);

    }
}
