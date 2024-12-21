package com.tlm.registration.verification;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlm.core.user.User;

@Service
public class VerificationService implements IVerficationService {

    @Autowired
    private IVerificationRepository verificationRepository;

    @Override
    public VerificationToken save(VerificationToken token) {
        return verificationRepository.save(token);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationRepository.findByToken(token);
    }

    public VerificationToken createToken(User user) {
        String token = generateToken();
        VerificationToken verificationToken = new VerificationToken(token, user);
        return save(verificationToken);
    }

    public User verifyToken(String token) throws VerificationTokenException {
        VerificationToken verificationToken = findByToken(token);
        if (verificationToken == null || verificationToken.getExpiryDate().before(new Date())) {
            throw new VerificationTokenException();
        }
        return verificationToken.getUser();
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

}
