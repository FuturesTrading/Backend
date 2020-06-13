package com.example.registerserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterserverdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterserverdemoApplication.class, args);
    }

}
