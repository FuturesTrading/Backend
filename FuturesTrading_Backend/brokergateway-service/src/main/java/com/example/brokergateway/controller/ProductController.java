package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Product;
import com.example.brokergateway.server.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/Product")
@RestController
public class ProductController {
    @Autowired
    public ProductServer productServer;

    @GetMapping("/getAll")
    public List<Product> getall(){
        return productServer.getAll();
    }
}
