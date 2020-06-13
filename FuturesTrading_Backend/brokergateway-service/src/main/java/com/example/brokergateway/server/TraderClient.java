package com.example.brokergateway.server;

import com.example.brokergateway.entity.Trade;
import com.example.demo.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traderGateway-service")
public interface TraderClient {

    @PostMapping(value = "/TraderGateway/trade")
    Result addTrade(@RequestBody Trade trade);

    @GetMapping(value = "/TraderGateway/orderBook/new")
    Result orderBookUpdateNew(Integer brokerId, Integer productId,Integer count,Float price,Boolean in_or_out);

    @GetMapping(value = "/TraderGateway/orderBook/none")
    Result orderBookUpdateDelete(Integer brokerId, Integer productId,Integer count,Float price,Boolean in_or_out);
}
