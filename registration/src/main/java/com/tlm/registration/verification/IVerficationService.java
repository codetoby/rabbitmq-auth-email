package com.tlm.registration.verification;

import com.tlm.core.user.User;

public interface IVerficationService {
    VerificationToken save(VerificationToken token);
    VerificationToken findByToken(String token);
    VerificationToken createToken(User user);
    User verifyToken(String token) throws VerificationTokenException;
}
