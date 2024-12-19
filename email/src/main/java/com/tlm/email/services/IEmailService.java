package com.tlm.email.services;

import com.tlm.core.dto.UserDTO;
import com.tlm.core.dto.VerificationRequestDTO;

public interface IEmailService {
    boolean sendVerificationEmail(VerificationRequestDTO userDTO);
    void sendAccountCreationEmail(UserDTO userDTO);
}
