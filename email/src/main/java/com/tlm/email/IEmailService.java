package com.tlm.email;

import com.tlm.dto.UserDTO;
import com.tlm.dto.VerificationRequestDTO;

public interface IEmailService {
    boolean sendVerificationEmail(VerificationRequestDTO userDTO);
    void sendAccountCreationEmail(UserDTO userDTO);
}
