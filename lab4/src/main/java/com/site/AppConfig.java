package com.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.site")
public class AppConfig {

    @Bean
    public Subject math() {
        return new Subject("Mathematics");
    }

    @Bean
    public Subject java() {
        return new Subject("Java Enterprise");
    }
}