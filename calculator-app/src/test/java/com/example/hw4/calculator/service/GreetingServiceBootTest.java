package com.example.hw4.calculator.service;

import com.example.hw4.calculator.config.ConversionConfig;
import com.example.hw4.calculator.config.UserConverter;
import com.example.hw4.calculator.i18n.MessageSourceMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {
                GreetingService.class,
                UserConverter.class,
                ConversionConfig.class,
                MessageSourceMessages.class,
                GreetingServiceBootTest.Config.class
        },
        properties = {
                "app.user={\"name\":\"Анна\",\"salutation\":\"товарищ\"}",
                "app.locale=en"
        }
)
class GreetingServiceBootTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    void shouldUseBootPropertiesConversionServiceAndLocalizedMessage() {
        assertEquals("Hello, товарищ Анна!", greetingService.greeting());
    }

    @Configuration
    static class Config {

        @Bean
        MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }
    }
}
