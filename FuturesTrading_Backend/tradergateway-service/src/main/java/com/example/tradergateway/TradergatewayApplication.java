package com.example.tradergateway;

import com.example.demo.annotation.FuturesTrading;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@FuturesTrading
public class TradergatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradergatewayApplication.class, args);
    }

}
