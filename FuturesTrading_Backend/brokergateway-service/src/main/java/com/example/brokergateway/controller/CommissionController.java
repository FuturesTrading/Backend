package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.server.BrokerServer;
import com.example.brokergateway.server.CommissionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("singleton")
@RestController
public class CommissionController {
    @Autowired
    public CommissionServer commissionServer;

    @PostMapping("/commission/add")
    public Boolean addCommission(@RequestParam Commission commission){
        return commissionServer.addOne(commission);
    }

    @PostMapping("/commission/set")
    public void setCommision(@RequestParam Integer broker_id,@RequestParam Integer product_id, @RequestParam Integer percent){
        commissionServer.set(broker_id,product_id,percent);
    }

    @PostMapping("/commission/getAll")
    public List<Commission> get(@RequestParam Integer broker_id){
        return commissionServer.getAll(broker_id);
    }
}
