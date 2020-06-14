package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.dto.TradeDTO;
import com.example.tradergateway.service.OrderBlotterService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class OrderBlotterController {
    @Autowired
    private OrderBlotterService orderBlotterService;

    @GetMapping(value = "/trade")
    public Result<List<TradeDTO>> getTradeByTraderId(@RequestParam Integer traderId){
        return ResultUtil.success(orderBlotterService.getTradeByTraderId(traderId));
    }

    @PostMapping(value = "/trade")
    public Result addTrade(@RequestBody TradeDTO tradeDTO) throws JSONException {
        orderBlotterService.updateOrderBlotter(tradeDTO);
        return ResultUtil.success();
    }
}
