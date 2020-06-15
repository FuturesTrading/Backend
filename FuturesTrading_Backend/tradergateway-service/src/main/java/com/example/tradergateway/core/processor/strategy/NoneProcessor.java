package com.example.tradergateway.core.processor.strategy;


import com.example.tradergateway.core.processor.Processor;
import com.example.tradergateway.dto.OrdersDTO;

import java.util.ArrayList;
import java.util.List;

public class NoneProcessor implements Processor {
    public List<OrdersDTO> process(OrdersDTO order) {
        List<OrdersDTO> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }
}
