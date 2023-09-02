package com.ccee.desafiocceebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.ccee.desafiocceebackend.*")
public class DesafioCceeBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesafioCceeBackendApplication.class, args);
    }
}
