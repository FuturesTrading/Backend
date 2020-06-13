package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Broker;

import java.util.List;

public interface BrokerDAO {
    Broker getOne(String input);
    List<Broker> getAll();
    Broker getByBrokerId(Integer brokerId);
}
