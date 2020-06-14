package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.server.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Scope("singleton")
@RestController
public class OrderController {
    @Autowired
    private OrderServer orderServer;

    @PostMapping("/orders/newOrder")
    public void addOrder(@RequestBody Orders orders){
        Integer type = orders.getVariety();
        orderServer.addOne(orders);
        orderServer.handle(orders,type);
    }

    @GetMapping("/orders/getOrders_handling")
    public List<Orders> getAll(@RequestParam Integer broker_id, @RequestParam Integer in_or_out,@RequestParam Integer product_id){
        Boolean tmp = in_or_out == 1;//0代表买入，即true是卖出
        return orderServer.getByBroker_id(broker_id,tmp,product_id);
    }

    @GetMapping("/orders/getOrders_handled")
    public List<Orders> getAll_handled(@RequestParam Integer broker_id, @RequestParam Integer in_or_out,@RequestParam Integer product_id){
        Boolean tmp = in_or_out == 1;//0代表买入，即true是卖出
        return orderServer.getByBroker_id_handled(broker_id,tmp,product_id);
    }

    @GetMapping("/orders/trader")
    public List<Orders> getOrdersByTraderId(@RequestParam Integer traderId){
        return orderServer.getOrdersByTraderId(traderId);
    }
}
