package com.example.demo.xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mixed-config")
@ImportResource("classpath:applicationContext.xml")
public class XmlNavigatorConfig {
}