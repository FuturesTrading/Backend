package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository  extends JpaRepository<Trade, Integer> {
    List<Trade> getAllByBrokerId(Integer broker_id);
}
