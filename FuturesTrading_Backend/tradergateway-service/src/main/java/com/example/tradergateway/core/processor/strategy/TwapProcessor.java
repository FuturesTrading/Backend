package com.example.tradergateway.core.processor.strategy;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.ServiceException;
import com.example.demo.response.ResultCode;
import com.example.demo.util.DateUtil;
import com.example.tradergateway.core.processor.Processor;
import com.example.tradergateway.dto.OrdersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TwapProcessor implements Processor {

    private final static Logger logger = LoggerFactory.getLogger("TwapProcessor");

    /**
     * Minute
     */
    private int interval;

    private Calendar startTime;
    private Calendar endTime;

    public TwapProcessor(Calendar startTime, Calendar endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TwapProcessor(){}


    public List<OrdersDTO> process(OrdersDTO order) {
        logger.info("[TwapProcessor] startTime: " + DateUtil.datetimeFormat.format(startTime.getTime()));
        logger.info("[TwapProcessor] endTime: " + DateUtil.datetimeFormat.format(endTime.getTime()));
        logger.info("[TwapProcessor.process] " + JSON.toJSONString(order));
        if(DateUtil.getMinuteInterval(startTime, endTime)<=0) {
            throw new ServiceException(ResultCode.WRONG_TIME);
        }
        int total = order.getQuantity();
        int slice = DateUtil.getMinuteInterval(startTime, endTime) / interval;
        int mean = total / slice;
        logger.info("[TwapProcessor.process] Slice: " + slice + " Mean: " + mean);

        List<OrdersDTO> orders = new ArrayList<>();

        int gap = total - mean * slice;

        for (int i = 0; i < slice; i++){
            OrdersDTO o = new OrdersDTO(order);
            o.setQuantity(mean);
            orders.add(o);
        }
        OrdersDTO temp = orders.get(0);
        temp.setQuantity(temp.getQuantity() + gap);
        return orders;
    }


    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
