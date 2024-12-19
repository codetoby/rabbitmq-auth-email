package com.tlm.email;

import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tlm.email.consumer.RabitMQConsumer;

@SpringBootApplication
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabitMQConsumer receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
