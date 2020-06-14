package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.BrokerDAO;
import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrokerDAOImpl implements BrokerDAO {
    @Autowired
    public BrokerRepository brokerRepository;

    @Override
    public Broker getOne(String broker_name) {
        return brokerRepository.findByBroker_nameEquals(broker_name);
    }

    @Override
    public List<Broker> getAll() {
        List<Broker> res = brokerRepository.findAll();
        Integer length = res.size();
        for(Integer a = 0; a < length; a++){
            res.get(a).setBrokerPassword("*****");
        }
        return res;
    }

    @Override
    public Broker getByBrokerId(Integer brokerId){
        return brokerRepository.findByBrokerId(brokerId);
    }
}
