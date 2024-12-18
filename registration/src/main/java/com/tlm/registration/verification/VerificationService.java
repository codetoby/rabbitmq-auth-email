package com.tlm.registration.verification;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.tlm.user.User;

public class VerificationService implements IVerficationService {

    @Autowired
    private VerificationRepository verificationRepository;

    @Override
    public VerificationToken save(VerificationToken token) {
        return verificationRepository.save(token);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUser(Long id) {
        return verificationRepository.findById(id).orElse(null);
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
