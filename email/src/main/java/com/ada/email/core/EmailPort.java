package com.ada.email.core;

import com.ada.email.adapter.JpaEmailEntity;
import com.ada.email.domain.EmailDTO;

public interface EmailPort {
    public JpaEmailEntity enviarEmail(EmailDTO email);
}
