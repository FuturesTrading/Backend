package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Info;
import com.example.brokergateway.server.OrderBookServer;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
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
    public Result<List<Info>> getOrderBookByBrokerIdAndProductId(@RequestParam Integer brokerId, @RequestParam Integer productId){
        return ResultUtil.success(orderBookServer.getOrderBookByBrokerIdAndProductId(brokerId,productId));
    }

}
