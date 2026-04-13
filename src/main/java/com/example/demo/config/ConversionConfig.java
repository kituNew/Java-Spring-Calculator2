package com.example.demo.config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;

@Configuration
public class ConversionConfig {

    @Bean(name = ConfigurableApplicationContext.CONVERSION_SERVICE_BEAN_NAME)
    public DefaultFormattingConversionService conversionService(UserConverter userConverter) {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(userConverter);
        return conversionService;
    }
}
