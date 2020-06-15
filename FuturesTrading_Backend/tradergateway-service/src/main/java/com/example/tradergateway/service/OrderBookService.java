package com.example.tradergateway.service;


import com.alibaba.fastjson.JSONObject;
import com.example.tradergateway.dto.OrdersDTO;
import com.example.tradergateway.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class OrderBookService {
    @Autowired
    private BrokerClient brokerClient;

    @Autowired
    private WebSocketServer webSocketServer;
    public List<OrdersDTO> getOrderBookByBrokerId(@RequestParam Integer brokerId){
        return brokerClient.getOrderBookByBrokerId(brokerId);
    }

    public void updateOrderBook(Integer brokerId){
        List<OrdersDTO> ordersDTOS=getOrderBookByBrokerId(brokerId);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orders",ordersDTOS);
        webSocketServer.sendMessageToAll(jsonObject.toString());
    }
}
