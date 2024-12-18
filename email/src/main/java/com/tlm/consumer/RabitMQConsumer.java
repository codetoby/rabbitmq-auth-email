package com.tlm.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tlm.constants.rabbitmq.RabbitMQConstants;
import com.tlm.dto.UserDTO;
import com.tlm.dto.VerificationRequestDTO;
import com.tlm.email.IEmailService;

@Component
public class RabitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabitMQConsumer.class);

    @Autowired
    private IEmailService emailService;

    @RabbitListener(queues = RabbitMQConstants.VERIFICATION_REQUEST_QUEUE)
    public void consumeMessage(VerificationRequestDTO verificationRequestDTO) {
        LOGGER.info("Received message: {}", verificationRequestDTO);
        try {
            emailService.sendVerificationEmail(verificationRequestDTO);
        } catch (Exception e) {
            LOGGER.error("Error processing message: {}", e.getMessage(), e);
        }
    }

    @RabbitListener(queues = RabbitMQConstants.ACCOUNT_CREATION_QUEUE)
    public void consumeAccountCreationMessage(UserDTO userDTO) {
        LOGGER.info("Received message: {}", userDTO);
        try {
            emailService.sendAccountCreationEmail(userDTO);
        } catch (Exception e) {
            LOGGER.error("Error processing message: {}", e.getMessage(), e);
        }
    }



}
