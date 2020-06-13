package com.example.tradergateway.dao;


import com.example.tradergateway.entity.Trade;

import java.util.List;

public interface TradeDAO {
    Boolean addOne(Trade input);
    Trade getOne(Integer trade_id);
    List<Trade> getByBroker_id(Integer broker_id);
}
