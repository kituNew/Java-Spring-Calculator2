package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.console.ConsoleIoService;
import com.example.demo.console.IoService;

@Configuration
@Profile("mixed-config")
public class IoConfig {

    @Bean
    public IoService ioService() {
        return new ConsoleIoService();
    }
}