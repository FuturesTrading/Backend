package com.example.traderservicedemo.service;

import com.example.traderservicedemo.dao.BrokerDao;
import com.example.traderservicedemo.model.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {
    @Autowired
    private BrokerDao brokerDao;

    public  Broker findByBroker_id(Integer id) {
        return brokerDao.findByBroker_id(id);
    }
}
