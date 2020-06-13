package com.example.tradergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TradergatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradergatewayApplication.class, args);
    }

}
