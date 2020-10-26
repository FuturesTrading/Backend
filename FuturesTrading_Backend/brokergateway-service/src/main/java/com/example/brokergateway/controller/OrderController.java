package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.server.OrderServer;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Scope("singleton")
@RestController
public class OrderController {
    @Autowired
    private OrderServer orderServer;
    
    @GetMapping("/getOrders_handling")
    public Result<List<Orders>> getAll(@RequestParam Integer broker_id){
        return ResultUtil.success(orderServer.getByBroker_id(broker_id));
    }

    @GetMapping("/getOrders_handled")
    public Result<List<Orders>> getAll_handled(@RequestParam Integer broker_id){
        return ResultUtil.success(orderServer.getByBroker_id_handled(broker_id));
    }

    @GetMapping("/orders/trader")
    public Result<List<Orders>> getOrdersByTraderId(@RequestParam Integer traderId){
        return ResultUtil.success(orderServer.getOrdersByTraderId(traderId));
    }
}
