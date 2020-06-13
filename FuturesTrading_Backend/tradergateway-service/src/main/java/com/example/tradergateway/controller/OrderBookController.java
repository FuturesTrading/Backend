package com.example.tradergateway.controller;

import com.example.demo.response.Result;
import com.example.demo.util.ResultUtil;
import com.example.tradergateway.dto.InfoDTO;
import com.example.tradergateway.service.OrderBookService;
import com.example.tradergateway.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.*;
import java.util.List;

@RestController
@RequestMapping("/TraderGateway")
public class OrderBookController {
    @Autowired
    private OrderBookService orderBookService;

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping(value="/orderBook/Buyer")
    public Result<List<InfoDTO>> getBuyOrderBook(Integer brokerId, Integer productId){
        return ResultUtil.success(orderBookService.getBuyOrderBook(productId,brokerId));
    }

    @GetMapping(value="/orderBook/Seller")
    public Result<List<InfoDTO>> getSellOrderBook(Integer brokerId, Integer productId){
        return ResultUtil.success(orderBookService.getSellOrderBook(productId,brokerId));
    }

    @GetMapping(value = "/orderBook")
    public Result<List<InfoDTO>> getOrderBook(Integer brokerId, Integer productId){
        return ResultUtil.success(orderBookService.getOrderBook(productId,brokerId));
    }

    @GetMapping(value = "/orderBook/new")
    public Result orderBookUpdateNew(Integer brokerId, Integer productId,Integer count,Float price,Boolean in_or_out){
        orderBookService.addOrderBook(productId,brokerId,count,price,in_or_out);
        List<InfoDTO> infos=orderBookService.getOrderBook(brokerId,productId);
        //需要调用websocket
        if(infos!=null && infos.size()!=0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("brokerId", brokerId);
            jsonObject.put("product", productId);
            jsonObject.put("orderBook", infos);
            webSocketServer.sendMessageToAll(jsonObject.toString());
        }
        return ResultUtil.success();
    }

    @GetMapping(value = "/orderBook/none")
    public Result orderBookUpdateDelete(Integer brokerId, Integer productId,Integer count,Float price,Boolean in_or_out){
        orderBookService.deleteOrderBook(productId,brokerId,count,price,in_or_out);
        List<InfoDTO> infos=orderBookService.getOrderBook(brokerId,productId);
        //需要调用websocket
        if(infos!=null && infos.size()!=0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("brokerId", brokerId);
            jsonObject.put("product", productId);
            jsonObject.put("orderBook", infos);
            webSocketServer.sendMessageToAll(jsonObject.toString());
        }
        return ResultUtil.success();
    }
}