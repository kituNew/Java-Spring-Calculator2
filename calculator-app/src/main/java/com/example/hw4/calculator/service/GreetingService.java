package com.example.hw4.calculator.service;

import com.example.hw4.calculator.i18n.Messages;
import com.example.hw4.calculator.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final User currentUser;
    private final Messages messages;

    public GreetingService(
            @Value("${app.user:Влад,милорд}") User currentUser,
            Messages messages
    ) {
        this.currentUser = currentUser;
        this.messages = messages;
    }

    public String greeting() {
        return messages.get("greeting", currentUser.displayName());
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
