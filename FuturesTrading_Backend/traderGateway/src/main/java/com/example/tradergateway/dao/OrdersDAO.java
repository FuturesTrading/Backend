package com.example.tradergateway.dao;

import com.example.tradergateway.entity.Orders;

import java.util.List;

public interface OrdersDAO {
    Boolean addOne(Orders input);
    Orders getOne(Integer input);
    List<Orders> getByBroker(Integer input, Boolean in_or_out, Integer product_id);
    Boolean setCease(Integer order_id, Integer state);
    Boolean setElse(Integer order_id, Integer state);
    Boolean decrease(Integer order_id, Integer amount);
    Boolean addCease(Orders orders);
    List<Orders> getCease(Integer input, Integer product_id, Boolean in_or_out);
}
