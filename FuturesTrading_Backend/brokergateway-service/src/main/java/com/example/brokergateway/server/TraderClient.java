package com.example.brokergateway.server;

import com.example.brokergateway.entity.Trade;
import com.example.demo.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "traderGateway-service")
public interface TraderClient {

    @PostMapping(value = "/trade")
    Result addTrade(@RequestBody Trade trade);

    @GetMapping(value = "/orderBook/new")
    Result orderBookUpdateNew(@RequestParam Integer brokerId, @RequestParam Integer productId, @RequestParam Integer count, @RequestParam Float price, @RequestParam Boolean in_or_out);

    @GetMapping(value = "/orderBook/none")
    Result orderBookUpdateDelete(@RequestParam Integer brokerId, @RequestParam Integer productId,@RequestParam Integer count,@RequestParam Float price,@RequestParam Boolean in_or_out);
}
