package com.example.traderservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TraderservicedemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TraderservicedemoApplication.class, args);
    }

}
