package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms // Enables JMS listener infrastructure
public class JmsApp {
    public static void main(String[] args) {
        SpringApplication.run(JmsApp.class, args);
    }
}
