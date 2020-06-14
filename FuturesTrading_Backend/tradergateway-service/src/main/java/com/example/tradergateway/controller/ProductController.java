package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.entity.Product;
import com.example.tradergateway.service.BrokerClient;
import com.example.tradergateway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product")
    public Result<List<Product>> getAllByBroker(){
        return ResultUtil.success(productService.getAllProduct());
    }

}
