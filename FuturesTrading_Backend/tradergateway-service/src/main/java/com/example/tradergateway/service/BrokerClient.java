package com.example.tradergateway.service;


import com.example.tradergateway.dto.BrokerDTO;
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

    @GetMapping("/orders/trader")
    List<OrdersDTO> getOrdersByTraderId(@RequestParam Integer traderId);

    @GetMapping("/getOrders_handling")
    List<OrdersDTO> getOrderBookByBrokerId(@RequestParam Integer brokerId);

    @GetMapping(value = "/trade/getByTrader")
    List<TradeDTO> getTradeByTraderId(@RequestParam Integer traderId);
}
