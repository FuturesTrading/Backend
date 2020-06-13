package com.example.traderservicedemo.service;


import com.example.traderservicedemo.core.processor.Processor;
import com.example.traderservicedemo.core.processor.ProcessorFactory;
import com.example.traderservicedemo.core.sendMessage.SendMessage;
import com.example.traderservicedemo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProcessorFactory processorFactory;

    @Autowired
    private SendMessage sendMessage;
    public Boolean createOrderWithStrategy(Order order, ProcessorFactory.Parameter pp, String startTime, String endTime, Integer slice, Integer intervalMinute) throws ParseException {
        Processor processor = processorFactory.create(pp);
        List<Order> orders = processor.process(order);
        try{
            sendMessage.productionDelayMessage(orders,startTime,intervalMinute);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
