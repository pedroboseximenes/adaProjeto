package com.ada.email.adapter;

import com.ada.email.enums.StatusEmail;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "email")
public class JpaEmailEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String emailFrom;

    @Column(nullable = false)
    private String emailTo;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime sendDataTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmail statusEmail;

    protected JpaEmailEntity() {}

    public JpaEmailEntity(StatusEmail statusEmail, LocalDateTime sendDataTime, String text, String subject, String emailTo, String emailFrom, Long id, Long userId) {
        this.statusEmail = statusEmail;
        this.sendDataTime = sendDataTime;
        this.text = text;
        this.subject = subject;
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSendDataTime() {
        return sendDataTime;
    }

    public void setSendDataTime(LocalDateTime sendDataTime) {
        this.sendDataTime = sendDataTime;
    }

    public StatusEmail getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
