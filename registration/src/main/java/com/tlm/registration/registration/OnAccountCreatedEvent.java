package com.tlm.registration.registration;

import org.springframework.context.ApplicationEvent;

import com.tlm.user.User;

import lombok.Getter;

@Getter
public class OnAccountCreatedEvent extends ApplicationEvent {

    private User user;

    public OnAccountCreatedEvent(User user) {
        super(user);
        this.user = user;
    }
    
}
