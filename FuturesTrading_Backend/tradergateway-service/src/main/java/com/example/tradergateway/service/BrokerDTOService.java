package com.example.tradergateway.service;


import com.example.tradergateway.dto.BrokerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerDTOService {
    @Autowired
    private BrokerClient brokerClient;

    public BrokerDTO findByBrokerId(Integer id) {
        return brokerClient.getBrokerByBrokerId(id);
    }
    public List<BrokerDTO> getAll() {
        return brokerClient.getAll();
    }
}
