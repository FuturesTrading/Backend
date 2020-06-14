package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT * from orders " +
            "WHERE trader_id = :input ",
            nativeQuery = true)
    List<Orders> findByTraderId(@Param("input")Integer trader_id);

    @Query(value = "SELECT * from orders " +
            "WHERE broker_id = :input ",
            nativeQuery = true)
    List<Orders> findAllByBroker_id(@Param("input")Integer input);

    @Query(value = "SELECT * from orders " +
            "WHERE broker_id = :input " +
            "AND state = :state ",
            nativeQuery = true)
    List<Orders> findByBroker_idAndStateAndProduct_id(
            @Param("input")Integer broker_id,
            @Param("state")Integer state);

    @Query(value = "SELECT * from orders " +
            "WHERE broker_id = :input " +
            "AND state = :state " +
            "AND class = :variety " +
            "AND product_id = :p " +
            "AND in_or_out = :in ",
            nativeQuery = true)
    List<Orders> findByBroker_idAndStateAndVarietyAndProduct_idAndIN(
            @Param("input")Integer broker_id,
            @Param("state")Integer state,
            @Param("variety")Integer variety,
            @Param("p")Integer product_id,
            @Param("in") Boolean in);
}
