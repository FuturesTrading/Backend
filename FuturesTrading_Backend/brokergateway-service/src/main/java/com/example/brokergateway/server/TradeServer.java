package com.example.brokergateway.server;

import com.example.brokergateway.DAO.TradeDAO;
import com.example.brokergateway.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServer{
    @Autowired
    public TradeDAO tradeDAO;

    public List<Trade> getByTrader(Integer trader_id) {
        return tradeDAO.getByTrader_id(trader_id);
    }


    public List<Trade> getByBroker(Integer broker_id) {
        return tradeDAO.getByBroker_id(broker_id);
    }
}
