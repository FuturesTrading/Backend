package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommissionDAOImpl implements CommissionDAO {
    @Autowired
    public CommissionRepository commissionRepository;

    @Override
    public Boolean addOne(Commission input) {
        input.setCommissionId(0);
        commissionRepository.save(input);
        return true;
    }

    @Override
    public Boolean modify( Integer commission_id, Integer percent) {
        Commission old = getOne(commission_id);
        old.setPercent(percent);
        commissionRepository.saveAndFlush(old);
        System.out.print( "modify succeed \n");
        return true;
    }

    @Override
    public Commission getOne(Integer commission_id) {
        return commissionRepository.getOne(commission_id);
    }
}
