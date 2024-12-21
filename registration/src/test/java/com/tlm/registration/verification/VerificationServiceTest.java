package com.tlm.registration.verification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tlm.core.user.User;

public class VerificationServiceTest {

    @Mock
    private IVerificationRepository verificationRepository;

    @InjectMocks
    private VerificationService verificationService;

    private VerificationToken verificationToken;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(1L, "user_email", "user_name", false);

        verificationToken = new VerificationToken(
            1L,
            "test_token",
            user,
            new Date(System.currentTimeMillis() + 60 * 24 * 60 * 1000L),
            false
        );

        when(verificationRepository.save(any())).thenReturn(verificationToken);
        when(verificationRepository.findByToken(verificationToken.getToken())).thenReturn(verificationToken);
    }

    @Test
    public void testSave() {
        assertEquals(verificationToken, verificationService.save(verificationToken));
    }

    @Test
    public void testFindByToken() {
        VerificationToken foundToken = verificationService.findByToken("test_token");
        assertEquals(verificationToken, foundToken);
    }

    @Test
    public void testCreateToken() {
        VerificationToken createdToken = verificationService.createToken(user);
        assertEquals(user, createdToken.getUser());
        assertEquals(10, createdToken.getToken().length());
    }

    @Test
    public void testVerifyToken() throws VerificationTokenException {
        User verifiedUser = verificationService.verifyToken("test_token");
        assertEquals(user, verifiedUser);

        assertThrows(VerificationTokenException.class, () -> verificationService.verifyToken("invalid"));
    }

    @Test
    public void testGenerateToken() {
        String token = VerificationService.generateToken();
        assertEquals(36, token.length());
    }
}
