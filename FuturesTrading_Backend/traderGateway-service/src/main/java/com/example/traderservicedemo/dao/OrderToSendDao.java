package com.example.traderservicedemo.dao;

import com.example.traderservicedemo.model.OrderToSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToSendDao extends JpaRepository<OrderToSend,Long> {
    public OrderToSend findById(String id);
    public void deleteById(String id);
}
