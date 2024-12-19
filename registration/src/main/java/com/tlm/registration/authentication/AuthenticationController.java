package com.tlm.registration.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlm.core.dto.UserRequestDTO;
import com.tlm.core.user.IUserService;
import com.tlm.core.user.User;
import com.tlm.registration.registration.OnRegistrationCompleteEvent;
import com.tlm.registration.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRequestDTO user,
            HttpServletRequest request) {
        try {
            System.out.println("Registering user: " + user);
            User savedUser = userService.createUser(user);
            String appUrl = request.getContextPath();
            System.out.println("User created: " + savedUser);
            applicationEventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(savedUser, appUrl));
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "Error during registration: " + e.getMessage(), 500));
        }
    }
}
