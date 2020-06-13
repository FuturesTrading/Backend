package com.example.traderservicedemo.controller;


import com.example.common.response.Result;
import com.example.common.util.ResultUtil;
import com.example.traderservicedemo.model.Trader;
import com.example.traderservicedemo.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraderController {
    @Autowired
    private TraderService traderService;

    @PostMapping(value="/trader")
    public Result traderSignUp(@RequestBody Trader trader){
        traderService.traderSignUp(trader);
        return ResultUtil.success();
    }

    @PostMapping(value = "/trader/newtrader")
    public Result traderRegister(@RequestBody Trader trader){
        traderService.traderRegister(trader);
        return ResultUtil.success();
    }

}
