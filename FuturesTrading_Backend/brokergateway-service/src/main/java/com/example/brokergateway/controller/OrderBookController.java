package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Info;
import com.example.brokergateway.server.OrderBookServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/Orderbook")
@RestController
public class OrderBookController {
    @Autowired
    private OrderBookServer orderBookServer;

    @GetMapping(value = "")
    public List<Info> getOrderBook(@RequestParam Integer brokerId,@RequestParam Integer productId){
        return orderBookServer.getOrderBook(brokerId,productId);
    }
}
