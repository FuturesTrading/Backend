package com.example.brokergateway.repository;

import com.example.brokergateway.entity.Broker;
import com.example.brokergateway.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import sun.nio.cs.ext.IBM037;

import java.util.List;

public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    @Query(value = "SELECT * FROM commission " +
            "WHERE commission_id = :a ",
            nativeQuery = true)
    Commission getOne(@Param("a")Integer commission_id);

    @Query(value = "SELECT * FROM commission " +
            "WHERE broker_id = :a " +
            "AND product_id = :b",
            nativeQuery = true)
    Commission getOne(@Param("a")Integer broker_id, @Param("b")Integer product_id);

    @Query(value = "SELECT * FROM commission " +
            "WHERE broker_id = :a " ,
            nativeQuery = true)
    List<Commission> getAll(@Param("a")Integer broker_id);
}
