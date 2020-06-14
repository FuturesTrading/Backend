package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrokerRepository  extends JpaRepository<Broker, Integer> {

    @Query(value = "SELECT * FROM broker WHERE " +
            "broker.broker_name = :input",
            nativeQuery = true)
    Broker findByBroker_nameEquals(@Param("input")String input);

    Broker findByBrokerId(Integer brokerId);
}
