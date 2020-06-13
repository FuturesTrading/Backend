package com.example.tradergateway.service;


import com.example.tradergateway.dto.BrokerDTO;
import com.example.tradergateway.dto.InfoDTO;
import com.example.tradergateway.dto.OrdersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "brokerGateway-service")
public interface BrokerClient {
    @GetMapping("/Broker/getAll")
    List<BrokerDTO> getAll();

    @GetMapping("/Broker/brokerId")
    BrokerDTO getBrokerByBrokerId(Integer brokerId);

    @GetMapping("/Orders/trader")
    List<OrdersDTO> getOrdersByTraderId(@RequestParam Integer traderId);

    @GetMapping(value = "/BrokerGateway/orderBook/broker/product")
    public List<InfoDTO> getOrderBookByBrokerIdAndProductId(@RequestParam Integer brokerId, @RequestParam Integer productId)
}
