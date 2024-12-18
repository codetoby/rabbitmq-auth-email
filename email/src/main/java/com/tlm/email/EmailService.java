package com.tlm.email;

import com.tlm.dto.UserDTO;
import com.tlm.dto.VerificationRequestDTO;

public class EmailService implements IEmailService {

    @Override
    public boolean sendVerificationEmail(VerificationRequestDTO userDTO) {
        System.out.println("Sending welcome email to " + userDTO.getEmail());
        return true;
    }

    @Override
    public void sendAccountCreationEmail(UserDTO userDTO) {
        System.out.println("Sending account creation email to " + userDTO.getEmail());
    }
    
}
