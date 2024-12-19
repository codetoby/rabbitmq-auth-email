package com.tlm.registration.producer;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tlm.core.constants.rabbitmq.RabbitMQConstants;
import com.tlm.core.dto.UserDTO;
import com.tlm.core.dto.VerificationRequestDTO;

@Component
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendVerificationRequest(VerificationRequestDTO verificationRequestDTO) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.VERIFICATION_REQUEST_KEY,
                    verificationRequestDTO);
            LOGGER.info("Message sent to exchange: {}, routing key: {}, message: {}",
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.VERIFICATION_REQUEST_KEY,
                    verificationRequestDTO);

        } catch (Exception e) {
            LOGGER.error("Error sending message to exchange: {}, routing key: {}",
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.VERIFICATION_REQUEST_KEY,
                    e);
        }
    }

    public void sendAccountCreationEmail(UserDTO userDTO) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.ACCOUNT_CREATION_KEY,
                    userDTO);
            LOGGER.info("Message sent to exchange: {}, routing key: {}, message: {}",
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.ACCOUNT_CREATION_KEY,
                    userDTO);

        } catch (Exception e) {
            LOGGER.error("Error sending message to exchange: {}, routing key: {}",
                    RabbitMQConstants.REGISTRATION_EXCHANGE,
                    RabbitMQConstants.ACCOUNT_CREATION_KEY,
                    e);
        }
    }
}