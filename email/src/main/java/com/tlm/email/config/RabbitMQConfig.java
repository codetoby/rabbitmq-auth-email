package com.tlm.email.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tlm.core.constants.rabbitmq.RabbitMQConstants;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue verificationRequestQueue() {
        return new Queue(RabbitMQConstants.VERIFICATION_REQUEST_QUEUE, false);
    }

    @Bean
    public Queue accountCreatedQueue() {
        return new Queue(RabbitMQConstants.ACCOUNT_CREATION_QUEUE, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitMQConstants.REGISTRATION_EXCHANGE);
    }

    @Bean
    public Binding verificationRequestBinding(
            @Qualifier("verificationRequestQueue") Queue verificationRequestQueue, 
            DirectExchange exchange) {
        return BindingBuilder.bind(verificationRequestQueue)
                .to(exchange)
                .with(RabbitMQConstants.VERIFICATION_REQUEST_KEY);
    }

    @Bean
    public Binding accountCreatedBinding(
            @Qualifier("accountCreatedQueue") Queue accountCreatedQueue, 
            DirectExchange exchange) {
        return BindingBuilder.bind(accountCreatedQueue)
                .to(exchange)
                .with(RabbitMQConstants.ACCOUNT_CREATION_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
