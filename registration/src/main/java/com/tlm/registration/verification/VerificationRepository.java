package com.tlm.registration.verification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
