package com.ada.email.adapter;

import com.ada.email.core.EmailPort;
import com.ada.email.domain.EmailDTO;
import com.ada.email.enums.StatusEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

public class EmailJpaAdapter implements EmailPort {
    private final EmailRepository jpa;
    final JavaMailSender emailSender;

    public EmailJpaAdapter(EmailRepository jpa, JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.jpa = jpa;
    }
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public JpaEmailEntity enviarEmail(EmailDTO dto) {
        JpaEmailEntity emailModel = new JpaEmailEntity();
        try{
            emailModel.setSendDataTime(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);
            emailModel.setUserId(dto.getUserId());
            emailModel.setSubject(dto.getSubject());
            emailModel.setText(dto.getText());
            emailModel.setEmailTo(dto.getEmailTo());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return jpa.save(emailModel);
        }
    }
}
