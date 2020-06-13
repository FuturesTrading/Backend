package com.example.tradergateway.repository;


import com.example.tradergateway.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {
    Commission findByCommission_id(Integer input);
}
