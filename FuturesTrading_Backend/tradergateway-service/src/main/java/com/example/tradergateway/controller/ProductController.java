package com.example.tradergateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.service.BrokerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private BrokerClient brokerClient;
    @GetMapping("/product/broker")
    public Result<List<JSONObject>> getAllByBroker(@RequestParam Integer brokerId){
        return ResultUtil.success(brokerClient.getAll());
    }
}
