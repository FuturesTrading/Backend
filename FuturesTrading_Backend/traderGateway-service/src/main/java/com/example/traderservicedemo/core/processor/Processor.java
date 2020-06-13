package com.example.traderservicedemo.core.processor;

import com.example.traderservicedemo.model.Order;

import java.util.List;

public interface Processor {
    List<Order> process(Order order);
}
