package com.example.tradergateway.dao;


import com.example.tradergateway.entity.Broker;

import java.util.List;

public interface BrokerDAO {
    Broker getOne(String input);
    List<Broker> getAll();
}
