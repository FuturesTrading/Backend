package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TraderGateway")
public class OrderBookController {
    @Autowired
    private OrderBookService orderBookService;

    @GetMapping(value="/orderBook")
    public Result<List<InfoDTO>> findOrderBookByBrokerIdAndProductId(Integer brokerId,Integer productId){
        return ResultUtil.success(orderBookService.findOrderBookByBrokerIdAndProductId(brokerId,productId));
    }
}
