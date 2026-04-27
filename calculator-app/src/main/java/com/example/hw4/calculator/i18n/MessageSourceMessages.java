package com.example.hw4.calculator.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSourceMessages implements Messages {

    private final MessageSource messageSource;
    private final Locale locale;

    public MessageSourceMessages(
            MessageSource messageSource,
            @Value("${app.locale:ru}") String localeTag
    ) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public String get(String code, Object... args) {
        return messageSource.getMessage(code, args, code, locale);
    }
}
