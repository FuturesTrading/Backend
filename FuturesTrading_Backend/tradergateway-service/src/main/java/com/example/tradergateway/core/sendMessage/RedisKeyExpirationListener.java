package com.example.tradergateway.core.sendMessage;

import com.example.tradergateway.dao.OrderToSendDao;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.entity.OrderToSend;
import com.example.tradergateway.kafka.KafkaOrderProducer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    private final static Logger logger = LoggerFactory.getLogger("TwapProcessor");

    @Autowired
    private OrderToSendDao orderToSendDao;

    @Autowired
    KafkaOrderProducer kafkaOrderProducer;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String orderCode = message.toString();
        if (!StringUtils.isBlank(orderCode)) {
            OrderToSend orderToSend=orderToSendDao.getById(Integer.parseInt(orderCode));
            OrdersDTO order=orderToSend.getOrder();
            order.setReleaseTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            logger.info("kafka send");
            kafkaOrderProducer.sendKafka(order);
            orderToSendDao.deleteById(Integer.parseInt(orderCode));
        }
        System.out.println("过期的订单号是: " + orderCode);
    }
}
