package com.ada.companhia_aerea.producers;

import com.ada.companhia_aerea.adapter.passagem.JpaPassagemEntity;
import com.ada.companhia_aerea.domain.EmailDTO;
import com.ada.companhia_aerea.domain.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ada.companhia_aerea.domain.Voo;
import java.time.format.DateTimeFormatter;

@Component
public class PassagemProducer {
    final RabbitTemplate rabbitTemplate;

    public PassagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(JpaPassagemEntity passagem, Voo voo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = voo.dataHora().format(formatter);
        StringBuilder sb = new StringBuilder();
        var emailDto = new EmailDTO();
        
        emailDto.setUserId(passagem.getUser_id());
        emailDto.setEmailTo(passagem.getEmail());
        emailDto.setSubject("Passagem processada com sucesso!");

        sb.append(passagem.getNome())
        .append(", você irá viajar para ")
        .append(voo.destino())
        .append(" com origem em ")
        .append(voo.origem())
        .append(".\n")
        .append("Preço : R$ ")
        .append(voo.preco())
        .append("\n")
        .append("Data : ")
        .append(dataFormatada)
        .append("\n")
        .append("Sua passagem foi processada com sucesso");

        emailDto.setText(sb.toString());

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
