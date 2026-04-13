package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.Salutation;
import com.example.demo.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("Свойство app.user не задано");
        }

        String trimmed = source.trim();
        if (trimmed.startsWith("{")) {
            return convertJson(trimmed);
        }
        return convertCsv(trimmed);
    }

    private User convertJson(String source) {
        Map<String, String> values = parseFlatJson(source);
        String name = values.get("name");
        String salutation = values.get("salutation");

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("В объекте пользователя отсутствует поле: name");
        }
        if (salutation == null || salutation.isBlank()) {
            throw new IllegalArgumentException("В объекте пользователя отсутствует поле: salutation");
        }

        return new User(name, Salutation.fromValue(salutation));
    }

    private User convertCsv(String source) {
        String[] parts = source.split("\\s*,\\s*", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Пользователь должен быть задан как JSON или CSV в формате 'имя,форма обращения'"
            );
        }
        return new User(parts[0], Salutation.fromValue(parts[1]));
    }

    private Map<String, String> parseFlatJson(String source) {
        String normalized = source.trim();
        if (!normalized.startsWith("{") || !normalized.endsWith("}")) {
            throw new IllegalArgumentException("Не удалось прочитать пользователя из JSON");
        }

        String body = normalized.substring(1, normalized.length() - 1).trim();
        Map<String, String> result = new HashMap<>();
        if (body.isEmpty()) {
            return result;
        }

        String[] pairs = body.split("\\s*,\\s*");
        for (String pair : pairs) {
            String[] keyValue = pair.split("\\s*:\\s*", 2);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Не удалось прочитать пользователя из JSON");
            }

            String key = stripQuotes(keyValue[0]);
            String value = stripQuotes(keyValue[1]);
            result.put(key, value);
        }
        return result;
    }

    private String stripQuotes(String value) {
        String result = value.trim();
        if (result.startsWith("\"") && result.endsWith("\"") && result.length() >= 2) {
            return result.substring(1, result.length() - 1);
        }
        return result;
    }
}
