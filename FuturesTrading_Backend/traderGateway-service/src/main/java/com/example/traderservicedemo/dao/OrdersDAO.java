package com.example.traderservicedemo.dao;


import com.example.traderservicedemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDAO extends JpaRepository<Order,Integer> {
    Boolean addOne(Order input);
    Boolean setCease(Integer order_id,Integer state);
    Boolean setElse(Integer order_id,Integer state);
    Boolean decrease(Integer order_id, Integer amount);
    Boolean addCease(Order order);
    List<Order> getCease(Integer input, Integer product_id, Boolean in_or_out );
}

