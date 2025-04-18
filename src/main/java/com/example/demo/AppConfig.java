package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${db-url}")
    private String url;

    @Value("${db-username}")
    private String username;

    @Value("${db-password}")
    private String password;

    @PostConstruct
    public void init() {
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
