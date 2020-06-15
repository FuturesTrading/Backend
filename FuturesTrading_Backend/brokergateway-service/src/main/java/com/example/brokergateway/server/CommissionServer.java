package com.example.brokergateway.server;

import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.entity.Commission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CommissionServer {
    @Autowired
    private CommissionDAO commissionDAO;


    public Boolean addOne(Commission commission) {
        return commissionDAO.addOne(commission);
    }

    public void set( Integer broker_id,  Integer product_id,  Integer percent){
        commissionDAO.modify(broker_id,product_id,percent);
    }
    public List<Commission> getAll(Integer broker_id) {
        return commissionDAO.getAll(broker_id);
    }
}
