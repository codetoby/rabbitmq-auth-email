package com.tlm.registration.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlm.core.user.IUserService;
import com.tlm.core.user.User;
import com.tlm.registration.registration.OnAccountCreatedEvent;
import com.tlm.registration.response.ApiResponse;
import com.tlm.registration.verification.IVerficationService;
import com.tlm.registration.verification.VerificationTokenException;

@RestController
public class VerificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);

    @Autowired
    private IVerficationService verificationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @RequestMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam("token") String token) {
        try {
            User user = verificationService.verifyToken(token);
            userService.setVerifiedTrue(user.getId());
            applicationEventPublisher.publishEvent(new OnAccountCreatedEvent(user));
            return ResponseEntity.ok(new ApiResponse(true, "User verified successfully", 200));
        } catch (VerificationTokenException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), 400));
        } catch (Exception e) {
            LOGGER.error("Error during registration: " + e);
            return ResponseEntity.internalServerError().body(new ApiResponse(false, "Error during registration: " + e.getMessage(), 500));
        }
    }
    
}
