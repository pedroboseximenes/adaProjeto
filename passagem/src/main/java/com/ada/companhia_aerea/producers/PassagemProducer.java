package com.ada.companhia_aerea.producers;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.domain.EmailDTO;
import com.ada.companhia_aerea.domain.Passagem;
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
        emailDto.setEmailTo(passagem.getEmail_passageiro());
        emailDto.setSubject("Passagem processada com sucesso!");
        emailDto.setText(passagem.getNome_passageiro() + ", você irá viajar para seu destino e sua passagem foi processada com sucesso");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
