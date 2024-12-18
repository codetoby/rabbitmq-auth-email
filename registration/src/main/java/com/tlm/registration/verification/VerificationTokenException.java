package com.tlm.registration.verification;

public class VerificationTokenException extends RuntimeException {
    private static final String MESSAGE = "Invalid Token";
    public VerificationTokenException() {
        super(MESSAGE);
    }
}
