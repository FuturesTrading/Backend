package com.example.traderservicedemo.core.sendMessage;


import com.example.common.util.DateUtil;
import com.example.traderservicedemo.dao.OrderToSendDao;
import com.example.traderservicedemo.model.Order;
import com.example.traderservicedemo.model.OrderToSend;
import com.example.traderservicedemo.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Component
public class SendMessage {

    @Autowired
    private OrderToSendDao orderToSendDao;

    @Autowired
    private RedisUtil redisUtil;
    public void productionDelayMessage(List<Order> orders,String startTime, Integer intervalMinute) throws ParseException {

        Calendar cur = DateUtil.stringToCalendar(startTime, DateUtil.datetimeFormat);

        for (Order order : orders) {
            if (order.getQuantity() == 0)
                continue;
            OrderToSend ots = new OrderToSend();
            String id=UUID.randomUUID().toString();
            ots.setDatetime(DateUtil.calendarToString(cur, DateUtil.datetimeFormat));

            ots.setId(id);
            ots.setOrder(order);

            redisUtil.set(id,ots,cur.getTimeInMillis()-Calendar.getInstance().getTime().getTime());
            orderToSendDao.save(ots);
            cur.add(Calendar.MINUTE, intervalMinute);

        }
    }

}
