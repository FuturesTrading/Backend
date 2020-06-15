package com.example.tradergateway.kafka;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.tradergateway.dto.OrdersDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public Boolean sendKafka(OrdersDTO object) {
        try {

            String order = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
            System.out.println(order);
            kafkaTemplate.send("orders", order);
            System.out.print("send success");
            return true;
        } catch (Exception e) {
            System.out.print("send false");
            return false;
        }
    }
}
