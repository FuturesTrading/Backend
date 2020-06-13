package com.example.traderservicedemo.dao;

import com.example.traderservicedemo.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerDao extends JpaRepository<Broker,Long> {
        public Broker findByBroker_id(Integer broker_id);

}
