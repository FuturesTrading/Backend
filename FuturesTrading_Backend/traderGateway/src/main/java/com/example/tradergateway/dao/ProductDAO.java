package com.example.tradergateway.dao;



import com.example.tradergateway.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getOne(Integer input);
    List<Product> getAll();
    Boolean addOne(Product input);
}
