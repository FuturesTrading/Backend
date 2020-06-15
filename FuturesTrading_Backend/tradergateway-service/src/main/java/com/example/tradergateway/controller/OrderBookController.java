package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.service.OrderBookService;
import com.example.tradergateway.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.*;
import java.util.List;

@RestController
public class OrderBookController {
    @Autowired
    private OrderBookService orderBookService;

    @GetMapping(value = "/orderBook")
    public Result<List<OrdersDTO>> getOrderBook(@RequestParam Integer brokerId){
        return ResultUtil.success(orderBookService.getOrderBookByBrokerId(brokerId));
    }

    //broker调用
    @GetMapping(value = "/orderBook/new")
    public Result orderBookUpdateNew(@RequestParam Integer brokerId){
        orderBookService.updateOrderBook(brokerId);
        return ResultUtil.success();
    }

}
