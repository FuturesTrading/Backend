package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.server.BrokerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/Broker")
@RestController
public class BrokerController {
    @Autowired
    public BrokerServer brokerServer;

    @GetMapping("/login")
    public Boolean login(@RequestParam String broker_name, @RequestParam String broker_password){
        return brokerServer.Login(broker_name,broker_password);
    }

    @GetMapping("/getAll")
    public List<Broker> getAll(){
        return brokerServer.getAll();
    }

    @GetMapping("/brokerId")
    public Broker getBrokerByBrokerId(Integer brokerId){
        return brokerServer.getByBrokerId(brokerId);
    }
}
