package com.example.tradergateway.core.sendMessage;

import com.example.tradergateway.dao.OrderToSendDao;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.kafka.KafkaOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Component
public class SendMessageInstant {
    @Autowired
    KafkaOrderProducer kafkaOrderProducer;
    public void productionInstantMessage(List<OrdersDTO> orders){
        System.out.println(orders.size());
        for(OrdersDTO order:orders){
            order.setReleaseTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).getTimeInMillis()));
            kafkaOrderProducer.sendKafka(order);
        }
    }
}
