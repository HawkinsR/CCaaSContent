package com.revature.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // <--- Critical: Turns this Spring Boot app into a centralized Configuration
                    // Server
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

// Ensure the application.yml contains:
/*
 * server:
 * port: 8888
 * spring:
 * cloud:
 * config:
 * server:
 * git:
 * uri: https://github.com/my-org/my-central-config-repo
 */
