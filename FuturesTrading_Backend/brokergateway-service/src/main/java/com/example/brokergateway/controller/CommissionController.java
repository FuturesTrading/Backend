package com.example.brokergateway.controller;

import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.entity.Product;
import com.example.brokergateway.server.BrokerServer;
import com.example.brokergateway.server.CommissionServer;
import com.example.brokergateway.server.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@RestController
public class CommissionController {
    @Autowired
    public CommissionServer commissionServer;

    @Autowired
    public ProductServer productServer;

    @PostMapping("/commission/add")
    public Boolean addCommission(@RequestBody Commission commission){
        productServer.addOne(commission.getProductId());
        return commissionServer.addOne(commission);
    }

    @GetMapping("/commission/getAll")
    public List<Product> get(@RequestParam Integer brokerId){
        List<Commission> tmp = commissionServer.getAll(brokerId);
        List<Product> res = new ArrayList<>();
        for(Commission a:tmp){
            res.add(productServer.getOne(a.getProductId()));
        }
        return res;
    }


}
