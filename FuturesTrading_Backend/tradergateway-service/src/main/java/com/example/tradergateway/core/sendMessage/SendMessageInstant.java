package com.example.tradergateway.core.sendMessage;

import com.example.tradergateway.dao.OrderToSendDao;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.kafka.KafkaOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMessageInstant {
    @Autowired
    KafkaOrderProducer kafkaOrderProducer;
    public void productionInstantMessage(List<OrdersDTO> orders){
        for(OrdersDTO order:orders)
        kafkaOrderProducer.sendKafka(order);
    }
}
