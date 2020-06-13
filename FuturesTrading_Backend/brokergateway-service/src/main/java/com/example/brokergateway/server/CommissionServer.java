package com.example.brokergateway.server;

import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.entity.Commission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionServer {
    @Autowired
    private CommissionDAO commissionDAO;


    public Boolean addOne(Commission commission) {
        return commissionDAO.addOne(commission);
    }


    public Commission getOne(Integer commission_id) {
        return commissionDAO.getOne(commission_id);
    }
}
