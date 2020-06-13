package com.example.tradergateway.service;


import com.example.tradergateway.core.processor.Processor;
import com.example.tradergateway.core.processor.ProcessorFactory;
import com.example.tradergateway.core.sendMessage.SendMessage;
import com.example.tradergateway.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class OrdersDTOService {
    @Autowired
    private ProcessorFactory processorFactory;

    @Autowired
    private BrokerClient brokerClient;

    @Autowired
    private SendMessage sendMessage;
    public Boolean createOrderWithStrategy(OrdersDTO order, ProcessorFactory.Parameter pp, String startTime, String endTime, Integer slice, Integer intervalMinute) throws ParseException {
        Processor processor = processorFactory.create(pp);
        List<OrdersDTO> orders = processor.process(order);

        sendMessage.productionDelayMessage(orders,startTime,intervalMinute);
        return true;
    }

    public List<OrdersDTO> findOrderByTraderId(Integer id) {
        return brokerClient.getOrdersByTraderId(id);
    }
}
