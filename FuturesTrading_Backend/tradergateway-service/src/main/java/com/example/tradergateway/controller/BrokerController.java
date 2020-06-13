package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.dto.BrokerDTO;
import com.example.tradergateway.service.BrokerDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrokerController {
    @Autowired
    private BrokerDTOService brokerDTOService;

    @GetMapping
    public Result<List<BrokerDTO>> getAllBroker(){
        return ResultUtil.success(brokerDTOService.getAll());
    }
}
