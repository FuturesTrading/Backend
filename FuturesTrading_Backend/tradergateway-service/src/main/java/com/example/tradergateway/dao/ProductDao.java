package com.example.tradergateway.dao;

import com.example.tradergateway.entity.OrderToSend;
import com.example.tradergateway.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
    List<Product> findAll();
    List<Product> findByProductName(String name);
    Product findByProductId(Integer id);
    List<Product> findByProductNameAndPeriod(String name,String period);
}
