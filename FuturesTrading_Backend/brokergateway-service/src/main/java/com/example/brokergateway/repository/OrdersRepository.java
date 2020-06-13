package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findAllByBrokerId(Integer input);

    List<Orders> findByBrokerIdAndStateAndVarietyAndProductId(Integer broker_id, Integer state, Integer variety, Integer product_id);

    List<Orders> findByTraderId(Integer traderId);
}
