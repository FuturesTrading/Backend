package com.example.traderservicedemo.core.processor;

import com.example.common.exception.InvalidParameterException;
import com.example.common.util.DateUtil;
import com.example.traderservicedemo.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.traderservicedemo.core.processor.strategy.*;
import java.text.ParseException;
import java.util.Calendar;

@Component
public class ProcessorFactory {
    @Autowired
    private BrokerService brokerService;

    public final static String VWAP = "VWAP";
    public final static String TWAP = "TWAP";
    public final static String ICE = "ICE";
    public final static String NONE = "NONE";

    public static class Parameter{
        private String strategy;
        private Calendar startTime;
        private Calendar endTime;
        private Integer slice;
        private Integer brokerId;
        private Integer intervalMinute;

        public Parameter(String strategy, String startTime, String endTime, Integer slice, Integer brokerId, Integer intervalMinute) throws ParseException {
            if (strategy.equals(VWAP) && brokerId == null)
                throw new InvalidParameterException("brokerId must not be null when using VWAP");
            this.startTime = DateUtil.stringToCalendar(startTime, DateUtil.datetimeFormat);
            this.endTime = DateUtil.stringToCalendar(endTime, DateUtil.datetimeFormat);
            this.strategy = strategy;
            this.slice = slice;
            this.brokerId = brokerId;
            this.intervalMinute = intervalMinute;
        }

        public Parameter(){}

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
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

        public Integer getSlice() {
            return slice;
        }

        public void setSlice(Integer slice) {
            this.slice = slice;
        }

        public Integer getBrokerId() {
            return brokerId;
        }

        public void setBrokerId(Integer brokerId) {
            this.brokerId = brokerId;
        }

        public Integer getIntervalMinute() {
            return intervalMinute;
        }

        public void setIntervalMinute(Integer intervalMinute) {
            this.intervalMinute = intervalMinute;
        }
    }

    public Processor create(Parameter parameter){
        Processor p;
        switch (parameter.getStrategy()){
            case TWAP:
                TwapProcessor tp = new TwapProcessor();
                tp.setStartTime(parameter.getStartTime());
                tp.setEndTime(parameter.getEndTime());
                tp.setInterval(parameter.getIntervalMinute());
                p = tp;
                break;
            case NONE:
                p = new NoneProcessor();
                break;
            default:
                throw new InvalidParameterException("Unknown Processor Strategy: " + parameter.getStrategy());
        }
        return p;
    }
}
