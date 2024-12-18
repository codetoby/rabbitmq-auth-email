package com.tlm.registration.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tlm.constants.rabbitmq.RabbitMQConstants;


@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitMQConstants.REGISTRATION_EXCHANGE);
    }

}