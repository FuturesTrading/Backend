package com.example.tradergateway.service;


import com.example.tradergateway.dto.InfoDTO;
import com.example.tradergateway.entity.OrderBook;
import org.apache.zookeeper.version.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderBookService {
    @Autowired
    private BrokerClient brokerClient;

    private OrderBook orderBook=OrderBook.getInstance();

    private void updateOrderBook(Integer productId, Integer brokerId){
        List<InfoDTO> infos=brokerClient.getOrderBookByBrokerIdAndProductId(brokerId,productId);
        for(InfoDTO infoDTO:infos){
            if(infoDTO.getBuy_level()!=null && !infoDTO.getBuy_level().equals("")){
                orderBook.addBuy(Float.parseFloat(infoDTO.getPrice()),Integer.parseInt(infoDTO.getBuy_vol()));
            }
        }
    }

    public List<InfoDTO> getBuyOrderBook(Integer productId, Integer brokerId){
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
        return infos;
    }

    public List<InfoDTO> getSellOrderBook(Integer productId, Integer brokerId){
        List<InfoDTO> infos=new ArrayList<>();
        Map<Float,Integer> orderBookMap=orderBook.getSellMap();
        List<Map.Entry<Float, Integer>> list = new ArrayList<Map.Entry<Float, Integer>>(orderBookMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Float, Integer>>() {
            @Override
            public int compare(Map.Entry<Float, Integer> o1, Map.Entry<Float, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        int level=1;
        for(Map.Entry<Float, Integer> entry:list){
            InfoDTO infoDTO=new InfoDTO(true,entry.getValue(),entry.getKey());
            infoDTO.setBuy_level(Integer.toString(level));
            infos.add(infoDTO);
            level++;
        }
        return infos;
    }
}
