package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Trade;
import com.example.brokergateway.server.TradeServer;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("singleton")
@RestController
public class TradeController {
    @Autowired
    public TradeServer tradeServer;

    @GetMapping("/trade/getByTrader")
    public Result<List<Trade>> getByTrader(@RequestParam Integer traderId){
        return ResultUtil.success(tradeServer.getByTrader(traderId));
    }

    @GetMapping("/trade/getByBroker")
    public Result<List<Trade>> getByBroker(@RequestParam Integer brokerId){
        return ResultUtil.success(tradeServer.getByBroker(brokerId));
    }
}