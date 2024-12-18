package com.tlm.registration.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import com.tlm.dto.VerificationRequestDTO;
import com.tlm.registration.producer.RabbitMQProducer;
import com.tlm.registration.verification.IVerficationService;
import com.tlm.registration.verification.VerificationToken;

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
        VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO(
                confirmUrl,
                event.getUser().getEmail(),
                event.getUser().getUsername());
        rabbitMQProducer.sendVerificationRequest(verificationRequestDTO);
    }

}
