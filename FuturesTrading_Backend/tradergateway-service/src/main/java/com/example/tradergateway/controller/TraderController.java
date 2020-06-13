package com.example.tradergateway.controller;


import com.example.demo.exception.ServiceException;
import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.entity.Trader;
import com.example.tradergateway.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraderController {
    @Autowired
    private TraderService traderService;

    @PostMapping(value="/trader")
    public Result traderSignUp(@RequestBody Trader trader) throws ServiceException{
        traderService.traderSignUp(trader);
        return ResultUtil.success();
    }

    @PostMapping(value = "/trader/newtrader")
    public Result traderRegister(@RequestBody Trader trader) throws ServiceException {
        traderService.traderRegister(trader);
        return ResultUtil.success();
    }

}
