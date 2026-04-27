package com.example.hw4.calculator.model;

public record User(String name, Salutation salutation) {

    public User {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя пользователя не задано");
        }
        if (salutation == null) {
            throw new IllegalArgumentException("Форма обращения не задана");
        }
    }

    public String displayName() {
        return salutation.getValue() + " " + name;
    }
}
