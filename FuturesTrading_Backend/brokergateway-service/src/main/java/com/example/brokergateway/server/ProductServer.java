package com.example.brokergateway.server;

import com.example.brokergateway.DAO.ProductDAO;
import com.example.brokergateway.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServer {
    @Autowired
    public ProductDAO productDAO;

    public List<Product> getAll() {
        return productDAO.getAll();
    }
}
