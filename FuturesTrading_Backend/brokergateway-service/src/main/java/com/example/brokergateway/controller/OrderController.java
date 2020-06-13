package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.server.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/Orders")
@RestController
public class OrderController {
    @Autowired
    private OrderServer orderServer;

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody Orders orders){
        Integer type = orders.getVariety();
        orderServer.addOne(orders);
        orderServer.handle(orders,type);

    }

    @GetMapping("/getAllOrders")
    public List<Orders> getAll(@RequestParam Integer broker_id, @RequestParam Integer in_or_out,@RequestParam Integer product_id){
        Boolean tmp = in_or_out == 1;//0代表买入，即true是卖出
        return orderServer.getByBroker_id(broker_id,tmp,product_id);
    }


    @GetMapping("/trader")
    public List<Orders> getOrdersByTraderId(@RequestParam Integer traderId){
        return orderServer.getByTraderId(traderId);
    }

}