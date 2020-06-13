package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Trade;

import java.util.List;

public interface TradeDAO {
    Boolean addOne(Trade input);
    Trade getOne(Integer trade_id);
    List<Trade> getByBroker_id(Integer broker_id);
}
