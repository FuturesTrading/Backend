package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Commission;

import java.util.List;

public interface CommissionDAO {
    Boolean addOne(Commission input);
    List<Commission> getAll(Integer broker_id);
}
