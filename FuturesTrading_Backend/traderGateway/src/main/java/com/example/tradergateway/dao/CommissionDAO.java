package com.example.tradergateway.dao;


import com.example.tradergateway.entity.Commission;

public interface CommissionDAO {
    Boolean addOne(Commission input);
    Boolean modify(Integer commission_id, Integer percent);
    Commission getOne(Integer commission_id);
}
