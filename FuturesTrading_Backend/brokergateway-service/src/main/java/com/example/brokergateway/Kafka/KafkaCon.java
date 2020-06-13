package com.example.brokergateway.Kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.server.OrderServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;


@Service
public class KafkaCon {

    private static Logger logger= LoggerFactory.getLogger("KafkaReceiver");

    @Autowired
    private OrderServer orderService;

//    public static void main(String[] args) throws Exception {
//        System.out.println("Consumer");
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "localhost:9092");
//        props.setProperty("group.id", "orders");
//        props.setProperty("enable.auto.commit", "true");
//        props.setProperty("auto.commit.interval.ms", "1000");
//        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
//        consumer.subscribe(Arrays.asList("orders"));
//
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//            for (ConsumerRecord<String, String> record : records){
//                System.out.println(record.value());
//                Orders orders = JSON.parseObject(record.value(),Orders.class);
//                System.out.println(orders.toString());
//                orderService.addOne(orders);
//            }
//        }
//    }


    @KafkaListener(topics = {"orders"})
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("receive message");
        System.out.println(record.value());
        Orders orders = JSON.parseObject(record.value(),Orders.class);
        System.out.println(orders.toString());
        orderService.addOne(orders);
    }
}
