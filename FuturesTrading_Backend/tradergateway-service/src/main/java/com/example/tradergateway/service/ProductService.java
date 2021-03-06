package com.example.tradergateway.service;

import com.example.tradergateway.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private BrokerClient brokerClient;

    public List<ProductDTO> getAllProductByBrokerId(Integer brokerId) {
        return brokerClient.get(brokerId);
    }

}
