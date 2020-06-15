package com.example.brokergateway.DAO;

import com.example.brokergateway.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getOne(Integer input);
    List<Product> getAll(Integer broker_id);
    Boolean addOne(Product input);
}
