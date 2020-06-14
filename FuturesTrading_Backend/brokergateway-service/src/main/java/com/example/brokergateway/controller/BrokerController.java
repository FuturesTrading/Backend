package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.server.BrokerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("singleton")
@RestController
public class BrokerController {
    @Autowired
    public BrokerServer brokerServer;

    @GetMapping("/broker/login")
    public Boolean login(@RequestParam String broker_name, @RequestParam String broker_password){
        return brokerServer.Login(broker_name,broker_password);
    }

    @GetMapping("/broker/getAll")
    public List<Broker> getAll(){
        return brokerServer.getAll();
    }

    @GetMapping("/broker/brokerId")
    public Broker getBrokerByBrokerId(@RequestParam Integer broker_id){
        return brokerServer.getByBrokerId(broker_id);
    }
}
