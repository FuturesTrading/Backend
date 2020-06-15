package com.example.tradergateway.service;


import com.example.tradergateway.dto.InfoDTO;
import com.example.tradergateway.entity.Market;
import com.example.tradergateway.entity.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderBookService {
    @Autowired
    private BrokerClient brokerClient;

    private Market market=Market.getInstance();

    public List<InfoDTO> getOrderBook(Integer productId, Integer brokerId) {
        List<InfoDTO> infos=brokerClient.getOrderBookByBrokerIdAndProductId(brokerId,productId);
        if(infos==null) return null;
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null) {
            orderBook = new OrderBook(productId, brokerId);
        }
        for (InfoDTO infoDTO : infos) {
            System.out.println(infoDTO.toString());
            if (infoDTO.getBuy_level() != null && !infoDTO.getBuy_level().equals("") && !infoDTO.getBuy_level().equals("0")) {
                orderBook.addBuy(Float.parseFloat(infoDTO.getPrice()), Integer.parseInt(infoDTO.getBuy_vol()));
            } else {
                orderBook.addSell(Float.parseFloat(infoDTO.getPrice()), Integer.parseInt(infoDTO.getSell_vol()));
            }
        }
        marketMap.put(key, orderBook);
        return infos;
    }

    public void addOrderBook(Integer productId, Integer brokerId, Integer count, Float price, Boolean in_or_out) {
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null){
            orderBook=new OrderBook(productId,brokerId);
        }
        if(in_or_out){
            orderBook.addSell(price,count);
        }
        else{
            orderBook.addBuy(price,count);
        }
        marketMap.put(key,orderBook);
    }

    public void deleteOrderBook(Integer productId, Integer brokerId, Integer count, Float price, Boolean in_or_out) {
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null){
            orderBook=new OrderBook(productId,brokerId);
        }
        if(in_or_out){
            orderBook.deleteSell(price,count);
        }
        else{
            orderBook.deleteBuy(price,count);
        }
        marketMap.put(key,orderBook);
    }
}
