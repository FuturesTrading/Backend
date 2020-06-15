package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.dto.ProductDTO;
import com.example.tradergateway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product")
    public Result<List<ProductDTO>> getAllByBroker(Integer brokerId){
        return ResultUtil.success(productService.getAllProductByBrokerId(brokerId));
    }
}
