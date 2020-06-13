package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, Integer> {
}
