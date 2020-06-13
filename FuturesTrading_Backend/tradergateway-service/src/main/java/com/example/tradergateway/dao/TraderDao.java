package com.example.tradergateway.dao;

import com.example.tradergateway.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraderDao extends JpaRepository<Trader,Integer> {
    Trader getByTraderId(Integer id);
    List<Trader>  getByTraderName(String name);
    List<Trader> getByTraderNameAndTraderPassword(String name,String pwd);
}
