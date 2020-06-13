package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Commission;

public interface CommissionDAO {
    Boolean addOne(Commission input);
    Boolean modify(Integer commission_id, Integer percent);
    Commission getOne(Integer commission_id);
}
