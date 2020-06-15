package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Trade;
import com.example.brokergateway.server.TradeServer;
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
    public List<Trade> getByTrader(@RequestParam Integer traderId){
        return tradeServer.getByTrader(traderId);
    }

    @GetMapping("/trade/getByBroker")
    public List<Trade> getByBroker(@RequestParam Integer brokerId){
        return tradeServer.getByBroker(brokerId);
    }
}