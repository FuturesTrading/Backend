package com.example.tradergateway.core.sendMessage;


import com.example.demo.exception.ServiceException;
import com.example.demo.response.ResultCode;
import com.example.demo.util.DateUtil;
import com.example.tradergateway.dao.OrderToSendDao;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.entity.OrderToSend;
import com.example.tradergateway.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Component
public class SendMessage {
    private final static Logger logger = LoggerFactory.getLogger("TwapProcessor");

    @Autowired
    private OrderToSendDao orderToSendDao;

    @Autowired
    private RedisUtil redisUtil;
    public void productionDelayMessage(List<OrdersDTO> orders, String startTime, Integer intervalMinute) throws ParseException,ServiceException {
        Calendar cur = DateUtil.stringToCalendar(startTime, DateUtil.datetimeFormat);

        if(cur.getTimeInMillis()-Calendar.getInstance().getTimeInMillis()<0){
            throw new ServiceException(ResultCode.WRONG_TIME_LATE);
        }
        for (OrdersDTO order : orders) {
            if (order.getQuantity() == 0)
                continue;
            OrderToSend ots = new OrderToSend();
            ots.setSendTime(DateUtil.calendarToString(cur, DateUtil.datetimeFormat));
            ots.setOrder(order);
            OrderToSend orderToSend=orderToSendDao.save(ots);
            redisUtil.setMillon(orderToSend.getId().toString(),ots,cur.getTimeInMillis()-Calendar.getInstance().getTimeInMillis());
            cur.add(Calendar.MINUTE, intervalMinute);
        }
    }

}
