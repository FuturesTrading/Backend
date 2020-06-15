package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.server.BrokerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Scope("singleton")
@RestController
public class BrokerController {
    @Autowired
    public BrokerServer brokerServer;

    @GetMapping("/broker/login")
    public Integer login(@RequestParam String brokerName, @RequestParam String brokerPassword){
        return brokerServer.Login(brokerName,brokerPassword);
    }

    @GetMapping("/broker/getAll")
    public List<Broker> getAll(){
        return brokerServer.getAll();
    }

    @GetMapping("/broker/brokerId")
    public Broker getBrokerByBrokerId(@RequestParam Integer brokerId){
        return brokerServer.getByBrokerId(brokerId);
    }
}
