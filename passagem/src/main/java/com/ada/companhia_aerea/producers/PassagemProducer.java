package com.ada.companhia_aerea.producers;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.domain.EmailDTO;
import com.ada.companhia_aerea.domain.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PassagemProducer {
    final RabbitTemplate rabbitTemplate;

    public PassagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(JpaPassagemEntity passagem) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(passagem.getUser_id());
        emailDto.setEmailTo(passagem.getEmail());
        emailDto.setSubject("Passagem processada com sucesso!");
        emailDto.setText(passagem.getNome() + ", você irá viajar para seu destino e sua passagem foi processada com sucesso");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

    public void publishMessageEmailError(UserDTO user) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(user.id());
        emailDto.setEmailTo(user.email());
        emailDto.setSubject("Passagem está com erro!");
        emailDto.setText("Caro Viajante, infelizmente houve um erro ao processar sua passagem. Por favor, tente novamente.");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
