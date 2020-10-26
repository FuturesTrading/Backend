package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.server.BrokerServer;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
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
    public Result<Integer> login(@RequestParam String brokerName, @RequestParam String brokerPassword){
        return ResultUtil.success(brokerServer.Login(brokerName,brokerPassword));
    }

    @GetMapping("/broker/getAll")
    public Result<List<Broker>> getAll(){
        return ResultUtil.success( brokerServer.getAll());
    }

    @GetMapping("/broker/brokerId")
    public Result getBrokerByBrokerId(@RequestParam Integer brokerId){
        return ResultUtil.success(brokerServer.getByBrokerId(brokerId));
    }
}
