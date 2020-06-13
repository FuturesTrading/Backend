package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Integer> {
}
