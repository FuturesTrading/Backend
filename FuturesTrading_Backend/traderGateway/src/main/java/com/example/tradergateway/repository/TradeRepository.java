package com.example.tradergateway.repository;


import com.example.tradergateway.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository  extends JpaRepository<Trade, Integer> {
    Trade getByTrade_id(Integer trade_id);
    List<Trade> getAllByBroker_id(Integer broker_id);
}
