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

    private OrderBook updateOrderBook(Integer productId, Integer brokerId){
        System.out.println("updateorderBook");
        List<InfoDTO> infos=brokerClient.getOrderBookByBrokerIdAndProductId(brokerId,productId);
        if(infos==null || infos.size()==0) return null;
        System.out.println(infos.toString());
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null) {
            orderBook = new OrderBook(productId, brokerId);
        }
        for (InfoDTO infoDTO : infos) {
            if (infoDTO.getBuy_level() != null && !infoDTO.getBuy_level().equals("")) {
                orderBook.addBuy(Float.parseFloat(infoDTO.getPrice()), Integer.parseInt(infoDTO.getBuy_vol()));
            } else {
                orderBook.addSell(Float.parseFloat(infoDTO.getPrice()), Integer.parseInt(infoDTO.getSell_vol()));
            }
        }
        marketMap.put(key, orderBook);
        return orderBook;
    }

    public List<InfoDTO> getBuyOrderBook(Integer productId, Integer brokerId){
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null){
            System.out.println("need orderBook");
            orderBook=updateOrderBook(productId,brokerId);
            if(orderBook==null) return null;
        }
        List<InfoDTO> infos=new ArrayList<>();
        Map<Float,Integer> orderBookMap=orderBook.getBuyMap();
        List<Map.Entry<Float, Integer>> list = new ArrayList<Map.Entry<Float, Integer>>(orderBookMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Float, Integer>>() {
            @Override
            public int compare(Map.Entry<Float, Integer> o1, Map.Entry<Float, Integer> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        int level=1;
        for(Map.Entry<Float, Integer> entry:list){
            InfoDTO infoDTO=new InfoDTO(true,entry.getValue(),entry.getKey());
            infoDTO.setBuy_level(Integer.toString(level));
            infos.add(infoDTO);
            level++;
        }
        marketMap.put(key,orderBook);
        return infos;
    }

    public List<InfoDTO> getSellOrderBook(Integer productId, Integer brokerId){
        Map<String,OrderBook> marketMap=market.getMarketMap();
        String key="product "+productId.toString()+"broker "+brokerId.toString();
        OrderBook orderBook=marketMap.get(key);
        if(orderBook==null){
            orderBook=updateOrderBook(productId,brokerId);
            if(orderBook==null) return null;
        }
        List<InfoDTO> infos=new ArrayList<>();
        Map<Float,Integer> orderBookMap=orderBook.getSellMap();
        List<Map.Entry<Float, Integer>> list = new ArrayList<Map.Entry<Float, Integer>>(orderBookMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Float, Integer>>() {
            @Override
            public int compare(Map.Entry<Float, Integer> o1, Map.Entry<Float, Integer> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        int level=list.size();
        for(Map.Entry<Float, Integer> entry:list){
            InfoDTO infoDTO=new InfoDTO(true,entry.getValue(),entry.getKey());
            infoDTO.setSell_level(Integer.toString(level));
            infos.add(infoDTO);
            level--;
        }
        return infos;
    }

    public List<InfoDTO> getOrderBook(Integer productId, Integer brokerId) {
        List<InfoDTO> infos=new ArrayList<>();
        List<InfoDTO> b=getSellOrderBook(productId, brokerId);
        if(b!=null) infos.addAll(b);
        List<InfoDTO> s=getSellOrderBook(productId, brokerId);
        if(s!=null) infos.addAll(s);
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
