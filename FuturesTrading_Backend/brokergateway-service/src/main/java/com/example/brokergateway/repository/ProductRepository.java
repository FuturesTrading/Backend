package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product " +
            "WHERE product_id = :a " ,
            nativeQuery = true)
    Product getOne(@Param("a")Integer product_id);
}
