package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository  extends JpaRepository<Trade, Integer> {
    @Query(value = "SELECT * from trade" +
            "WHERE broker_id = :input",
            nativeQuery = true)
    List<Trade> getAllByBroker_id(@Param("input") Integer broker_id);

    @Query(value = "SELECT * from trade" +
            "WHERE trader_id = :input",
            nativeQuery = true)
    List<Trade> getAllByTrader_id(@Param("input") Integer trader_id);
}
