package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Info;
import com.example.brokergateway.server.OrderBookServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("singleton")
@RestController
public class OrderBookController {
    @Autowired
    private OrderBookServer orderBookServer;

    @GetMapping(value = "/orderBook/broker/product")
    public List<Info> getOrderBookByBrokerIdAndProductId(@RequestParam Integer brokerId,@RequestParam Integer productId){
        return orderBookServer.getOrderBookByBrokerIdAndProductId(brokerId,productId);
    }

    @GetMapping(value = "/orderBook/product")
    public List<Info> getOrderBookByProductId(@RequestParam Integer productId){
        return orderBookServer.getOrderBookByProductId(productId);
    }

}
