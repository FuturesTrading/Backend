package com.example.tradergateway.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderBook {

    private Integer productId;
    private Integer brokerId;
    private Map<Float, Integer> buyMap = null;
    private Map<Float, Integer> sellMap = null;

    public OrderBook(){
    }

    public OrderBook(Integer productId,Integer brokerId)
    {
        buyMap = new HashMap<Float, Integer>();
        sellMap = new HashMap<Float, Integer>();
        this.productId = productId;
        this.brokerId=brokerId;
    }

    public void addBuy(Float price, Integer quantity)
    {
        if(buyMap.containsKey(price)){
            Integer count=buyMap.get(price);
            buyMap.put(price,quantity+count);
        }
        else{
            buyMap.put(price,quantity);
        }
    }

    public void addSell(Float price, int quantity)
    {
        if(sellMap.containsKey(price)){
            Integer count=sellMap.get(price);
            sellMap.put(price,quantity+count);
        }
        else{
            sellMap.put(price,quantity);
        }
    }

    public void deleteBuy(Float price,Integer quantity){
        if(buyMap.containsKey(price)){
            Integer count=buyMap.get(price);
            if(count<=quantity){
                buyMap.remove(price);
            }
            else {
                buyMap.put(price,count-quantity);
            }
        }
    }

    public void deleteSell(Float price,Integer quantity){
        if(sellMap.containsKey(price)){
            Integer count=sellMap.get(price);
            if(count<=quantity){
                sellMap.remove(price);
            }
            else {
                sellMap.put(price,count-quantity);
            }
        }
    }
}
