package com.example.tradergateway.core.processor;


import com.example.tradergateway.dto.OrdersDTO;

import java.util.List;

public interface Processor {
    List<OrdersDTO> process(OrdersDTO order);
}
