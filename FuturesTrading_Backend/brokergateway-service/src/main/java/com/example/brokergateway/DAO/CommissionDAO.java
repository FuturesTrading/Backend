package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Commission;

import java.util.List;

public interface CommissionDAO {
    Boolean addOne(Commission input);
    Boolean modify(Integer broker_id,Integer product_id, Integer percent);
    Integer getOne(Integer broker_id,Integer product_id);
    List<Commission> getAll(Integer broker_id);
}
