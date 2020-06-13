package com.example.traderservicedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public Boolean sendKafka(Object object) {
        try {
            String order =object.toString();
            kafkaTemplate.send("orders", order);
            System.out.print("send seccess");
            return true;
        } catch (Exception e) {
            System.out.print("send false");
            return false;
        }
    }
}
