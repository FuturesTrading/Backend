package com.example.tradergateway.service;

import com.example.tradergateway.dto.TradeDTO;
import com.example.tradergateway.entity.OrderBlotter;
import com.example.tradergateway.websocket.WebSocketServer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class OrderBlotterService {
    @Autowired
    private BrokerClient brokerClient;

    @Autowired
    private WebSocketServer webSocketServer;

    private OrderBlotter orderBlotter=OrderBlotter.getInstance();

    public List<TradeDTO> getTradeByTraderId(Integer traderId){
        List<TradeDTO> tradeDTOS=brokerClient.getTradeByTraderId(traderId);
        Collections.sort(tradeDTOS,new Comparator<TradeDTO>() {
            @Override
            public int compare(TradeDTO o1, TradeDTO o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });

        //更新orderBlotter
        Map<Integer,List<TradeDTO>> orderBlotterMap=orderBlotter.getTradeDTOMap();
        List<TradeDTO> newTradeDTOList=orderBlotterMap.get(traderId);
        if(newTradeDTOList==null || newTradeDTOList.size()==0){
            newTradeDTOList=new ArrayList<>();
            newTradeDTOList.addAll(tradeDTOS);
        }
        else{
            for(TradeDTO tradeDTO:tradeDTOS){
                if(!newTradeDTOList.contains(tradeDTO)){
                    newTradeDTOList.add(tradeDTO);
                }
            }
        }
        orderBlotterMap.put(traderId,newTradeDTOList);
        return tradeDTOS;
    }

    public void updateOrderBlotter(TradeDTO tradeDTO) throws JSONException {
        Integer traderId1=tradeDTO.getSellerId();
        Integer traderId2=tradeDTO.getBuyerId();
        Map<Integer,List<TradeDTO>> orderBlotterMap=orderBlotter.getTradeDTOMap();
        List<TradeDTO> newTradeDTOList1=orderBlotterMap.get(traderId1);
        if(newTradeDTOList1==null){
            newTradeDTOList1=new ArrayList<>();
        }
        newTradeDTOList1.add(tradeDTO);
        List<TradeDTO> newTradeDTOList2=orderBlotterMap.get(traderId2);
        if(newTradeDTOList2==null){
            newTradeDTOList2=new ArrayList<>();
        }
        newTradeDTOList2.add(tradeDTO);
        orderBlotterMap.put(traderId1,newTradeDTOList1);
        orderBlotterMap.put(traderId2,newTradeDTOList2);

        //调用websocket
        JSONObject jsonObject1=new JSONObject();
        newTradeDTOList1.sort(new Comparator<TradeDTO>() {
            @Override
            public int compare(TradeDTO o1, TradeDTO o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        jsonObject1.put("Trade",newTradeDTOList1);
        webSocketServer.sendMessage(traderId1,jsonObject1.toString());

        JSONObject jsonObject2=new JSONObject();
        newTradeDTOList2.sort(new Comparator<TradeDTO>() {
            @Override
            public int compare(TradeDTO o1, TradeDTO o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        jsonObject2.put("Trade",newTradeDTOList2);
        webSocketServer.sendMessage(traderId1,jsonObject2.toString());
    }
}
