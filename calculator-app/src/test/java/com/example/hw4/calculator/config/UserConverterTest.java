package com.example.hw4.calculator.config;

import com.example.hw4.calculator.model.Salutation;
import com.example.hw4.calculator.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {

    private final UserConverter converter = new UserConverter();

    @Test
    void shouldConvertCsvUser() {
        User user = converter.convert("Влад,милорд");

        assertEquals("Влад", user.name());
        assertEquals(Salutation.MILORD, user.salutation());
    }

    @Test
    void shouldConvertJsonUser() {
        User user = converter.convert("{\"name\":\"Анна\",\"salutation\":\"товарищ\"}");

        assertEquals("Анна", user.name());
        assertEquals(Salutation.TOVARISHCH, user.salutation());
    }
}
