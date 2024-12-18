package com.tlm.registration.verification;

import com.tlm.user.User;

public interface IVerficationService {
    VerificationToken save(VerificationToken token);
    VerificationToken findByToken(String token);
    VerificationToken findByUser(Long id);
    VerificationToken createToken(User user);
    User verifyToken(String token) throws VerificationTokenException;
}
