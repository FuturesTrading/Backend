package com.example.tradergateway.service;


import com.alibaba.fastjson.JSONObject;
import com.example.tradergateway.dto.BrokerDTO;
import com.example.tradergateway.dto.InfoDTO;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.dto.TradeDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "brokerGateway-service")
public interface BrokerClient {
    @GetMapping("/broker/getAll")
    List<BrokerDTO> getAll();

    @GetMapping("/broker/brokerId")
    BrokerDTO getBrokerByBrokerId(Integer brokerId);

    @GetMapping("/product/allProduct")
    List<JSONObject> getAllProduct();

    @GetMapping("/orders/trader")
    List<OrdersDTO> getOrdersByTraderId(@RequestParam Integer traderId);

    @GetMapping(value = "/orderBook/broker/product")
    List<InfoDTO> getOrderBookByBrokerIdAndProductId(@RequestParam Integer brokerId, @RequestParam Integer productId);

    @GetMapping(value = "/trade")
    List<TradeDTO> getTradeByTraderId(@RequestParam Integer traderId);
}
