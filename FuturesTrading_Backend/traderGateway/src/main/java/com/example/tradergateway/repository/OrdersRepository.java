package com.example.tradergateway.repository;


import com.example.tradergateway.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByBroker_id(Integer input);
    Orders findByOrder_id(Integer input);
    List<Orders> findByBroker_idAndStateAndVarietyAndProduct_id(Integer broker_id, Integer state, Integer variety, Integer product_id);
}
