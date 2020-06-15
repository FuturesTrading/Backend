package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommissionDAOImpl implements CommissionDAO {
    @Autowired
    public CommissionRepository commissionRepository;

    @Override
    public Boolean addOne(Commission input) {
        Commission res = commissionRepository.getOne(input.getCommissionId());
        if(res != null)
            res.setPercent(input.getPercent());
        commissionRepository.save(res);
        return true;
    }

    @Override
    public List<Commission> getAll(Integer broker_id) {
        return commissionRepository.getAll(broker_id);
    }

    @Override
    public Integer getOne(Integer broker_id, Integer product_id) {
        return commissionRepository.getOne(broker_id,product_id).getPercent();
    }


}
