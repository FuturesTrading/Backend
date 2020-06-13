package com.example.tradergateway.repository;


import com.example.tradergateway.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product getByProduct_id(Integer input);
    List<Product> getAll();
}
