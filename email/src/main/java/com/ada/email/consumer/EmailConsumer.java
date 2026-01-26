package com.ada.email.consumer;

import com.ada.email.core.SendEmailUseCase;
import com.ada.email.domain.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private final SendEmailUseCase sendEmailUseCase;

    public EmailConsumer(SendEmailUseCase sendEmailUseCase) {
        this.sendEmailUseCase = sendEmailUseCase;
    }

    @RabbitListener(queues= "${broker.queue.email.name}")
    public void receberMensagem(@Payload EmailDTO mensagem) {
        sendEmailUseCase.execute(mensagem);
    }

}
