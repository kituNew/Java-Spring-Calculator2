package com.example.hw4.io.autoconfigure;

import com.example.hw4.io.ConsoleIoService;
import com.example.hw4.io.IoService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(IoProperties.class)
@ConditionalOnProperty(prefix = "calculator.io", name = "enabled", havingValue = "true", matchIfMissing = true)
public class IoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IoService ioService() {
        return new ConsoleIoService();
    }
}
