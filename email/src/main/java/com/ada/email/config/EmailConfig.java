package com.ada.email.config;

import com.ada.email.adapter.EmailJpaAdapter;
import com.ada.email.adapter.EmailRepository;
import com.ada.email.core.EmailPort;
import com.ada.email.core.SendEmailUseCase;
import com.ada.email.validators.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@Configuration
public class EmailConfig {
    private final EmailRepository emailRepository;
    public EmailConfig(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Bean
    SendEmailUseCase sendEmailUseCase(EmailPort emailPort, List<Validator> validatorList) {
        return new SendEmailUseCase(emailPort, validatorList);
    }

    @Bean
    EmailPort emailPort(EmailRepository jpa, JavaMailSender emailSender) {
        return new EmailJpaAdapter(jpa, emailSender);
    }

}
