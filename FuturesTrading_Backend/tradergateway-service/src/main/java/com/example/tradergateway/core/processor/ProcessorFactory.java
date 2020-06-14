package com.example.tradergateway.core.processor;

import com.example.demo.exception.InvalidParameterException;
import com.example.demo.util.DateUtil;
import com.example.tradergateway.core.processor.strategy.NoneProcessor;
import com.example.tradergateway.core.processor.strategy.TwapProcessor;
import com.example.tradergateway.service.BrokerDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;

@Component
public class ProcessorFactory {
    @Autowired
    private BrokerDTOService brokerDTOService;

    public final static String VWAP = "VWAP";
    public final static String TWAP = "TWAP";
    public final static String ICE = "ICE";
    public final static String NONE = "NONE";

    public static class Parameter{
        private String strategy;
        private Calendar startTime;
        private Calendar endTime;
        private Integer brokerId;
        private Integer intervalMinute;

        public Parameter(String strategy, String startTime, String endTime,Integer brokerId, Integer intervalMinute) throws ParseException {
            if (strategy.equals(VWAP) && brokerId == null)
                throw new InvalidParameterException("brokerId must not be null when using VWAP");
            this.startTime = DateUtil.stringToCalendar(startTime, DateUtil.datetimeFormat);
            this.endTime = DateUtil.stringToCalendar(endTime, DateUtil.datetimeFormat);
            this.strategy = strategy;
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
                TwapProcessor tp1 = new TwapProcessor();
                tp1.setStartTime(parameter.getStartTime());
                tp1.setEndTime(parameter.getEndTime());
                tp1.setInterval(parameter.getIntervalMinute());
                p = tp1;
                break;
            default:
                throw new InvalidParameterException("Unknown Processor Strategy: " + parameter.getStrategy());
        }
        return p;
    }
}
