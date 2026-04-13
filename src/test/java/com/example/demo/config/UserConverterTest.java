package com.example.demo.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Salutation;
import com.example.demo.model.User;

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
