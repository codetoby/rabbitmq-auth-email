package com.tlm.registration.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tlm.core.dto.VerificationRequestDTO;
import com.tlm.registration.producer.RabbitMQProducer;
import com.tlm.registration.verification.IVerficationService;
import com.tlm.registration.verification.VerificationToken;

@Component
public class RegistrationEventListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IVerficationService verificationService;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Async
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        VerificationToken token = verificationService.createToken(event.getUser());

        String confirmUrl = event.getAppUrl() + "/verify?token=" + token.getToken();
        System.out.println("Confirm URL: " + confirmUrl);
        VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO(
                confirmUrl,
                event.getUser().getEmail(),
                event.getUser().getUsername());
        rabbitMQProducer.sendVerificationRequest(verificationRequestDTO);
    }

}
