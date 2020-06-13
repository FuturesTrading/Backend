package com.example.tradergateway.dao;

import com.example.tradergateway.entity.OrderToSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToSendDao extends JpaRepository<OrderToSend,Integer> {
    OrderToSend getById(Integer id);
}
