package com.example.brokergateway.server;


import com.example.brokergateway.DAO.BrokerDAO;
import com.example.brokergateway.entity.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerServer{
    @Autowired
    private BrokerDAO brokerDAO;

    public Boolean Login(String name, String password) {
        Broker broker = brokerDAO.getOne(name);
        if(broker == null){
            return false;//没有此用户
        }else{
            String answer = broker.getBrokerPassword();
            if(answer.equals(password)){
                return true;//成功
            }else {
                return false;//密码错误
            }
        }
    }

    public List<Broker> getAll() {
        return brokerDAO.getAll();
    }

    public Broker getByBrokerId(Integer brokerId) {
        return brokerDAO.getByBrokerId(brokerId);
    }
}