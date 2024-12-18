package com.tlm.registration.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import com.tlm.dto.UserDTO;
import com.tlm.registration.producer.RabbitMQProducer;

public class AccountCreationEventListener implements ApplicationListener<OnAccountCreatedEvent> {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Async
    @Override
    public void onApplicationEvent(OnAccountCreatedEvent event) {
        sendAccountCreationEmail(event);
    }

    private void sendAccountCreationEmail(OnAccountCreatedEvent event) {
        UserDTO userDTO = new UserDTO(event.getUser().getUsername(), event.getUser().getEmail());
        rabbitMQProducer.sendAccountCreationEmail(userDTO);
    }
    
}
