package com.example.tradergateway.repository;


import com.example.tradergateway.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository  extends JpaRepository<Broker, Integer> {
    Broker findByBroker_nameEquals(String input);
}
