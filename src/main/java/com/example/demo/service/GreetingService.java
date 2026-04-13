package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final User currentUser;

    public GreetingService(@Value("${app.user}") User currentUser) {
        this.currentUser = currentUser;
    }

    public String greeting() {
        return "Здравствуйте, " + currentUser.displayName() + "!";
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
