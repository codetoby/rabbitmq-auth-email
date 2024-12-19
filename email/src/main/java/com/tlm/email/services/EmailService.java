package com.tlm.email.services;

import org.springframework.stereotype.Service;

import com.tlm.core.dto.UserDTO;
import com.tlm.core.dto.VerificationRequestDTO;

@Service
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
