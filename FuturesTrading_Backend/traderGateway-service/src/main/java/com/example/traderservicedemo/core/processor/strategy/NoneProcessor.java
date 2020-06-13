package com.example.traderservicedemo.core.processor.strategy;


import com.example.traderservicedemo.core.processor.Processor;
import com.example.traderservicedemo.model.Order;

import java.util.ArrayList;
import java.util.List;

public class NoneProcessor implements Processor {

    public List<Order> process(Order order) {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }
}
