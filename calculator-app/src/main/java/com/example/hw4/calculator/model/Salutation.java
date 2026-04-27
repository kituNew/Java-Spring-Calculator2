package com.example.hw4.calculator.model;

public enum Salutation {
    GOSPODIN("господин"),
    TOVARISHCH("товарищ"),
    MILORD("милорд");

    private final String value;

    Salutation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Salutation fromValue(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("Форма обращения не задана");
        }

        String normalized = rawValue.trim();
        for (Salutation salutation : values()) {
            if (salutation.value.equalsIgnoreCase(normalized)
                    || salutation.name().equalsIgnoreCase(normalized)) {
                return salutation;
            }
        }

        throw new IllegalArgumentException("Неизвестная форма обращения: " + rawValue);
    }
}
