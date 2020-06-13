package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrokerRepository  extends JpaRepository<Broker, Integer> {

    Broker findByBrokerName(String input);

    Broker findByBrokerId(Integer brokerId);
}
