package com.example.tradergateway.service;


import com.alibaba.fastjson.JSONObject;
import com.example.tradergateway.dto.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "brokerGateway-service")
public interface BrokerClient {
    @GetMapping("/broker/getAll")
    List<BrokerDTO> getAll();

    @GetMapping("/broker/brokerId")
    BrokerDTO getBrokerByBrokerId(Integer brokerId);

    @GetMapping("/orders/trader")
    List<OrdersDTO> getOrdersByTraderId(@RequestParam Integer traderId);

    @GetMapping(value = "/orderBook/broker/product")
    List<InfoDTO> getOrderBookByBrokerIdAndProductId(@RequestParam Integer brokerId, @RequestParam Integer productId);

    @GetMapping(value = "/trade/getByTrader")
    List<TradeDTO> getTradeByTraderId(@RequestParam Integer traderId);

    @PostMapping("/commission/getAll")
    List<ProductDTO> get(@RequestParam Integer brokerId);
}
