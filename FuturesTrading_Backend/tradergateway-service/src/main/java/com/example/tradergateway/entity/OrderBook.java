package com.example.tradergateway.entity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderBook {
    private final Logger logger = LoggerFactory.getLogger(OrderBook.class);

    private Boolean init;
    private Integer productId;
    private Integer brokerId;
    private Map<Float, Integer> buyMap = null;
    private Map<Float, Integer> sellMap = null;

    private static OrderBook uniqueInstance = null;

    private OrderBook(){
        init=false;
    }

    public static synchronized OrderBook getInstance(){
        //判断存储实例的变量是否有值
        if(uniqueInstance == null){
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            uniqueInstance = new OrderBook();
        }
        //如果有值，那就直接使用
        return uniqueInstance;
    }

    public static synchronized OrderBook getInstance(Integer productId,Integer brokerId){
        //判断存储实例的变量是否有值
        if(uniqueInstance == null){
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            uniqueInstance = new OrderBook(productId,brokerId);
        }
        //如果有值，那就直接使用
        return uniqueInstance;
    }

    public OrderBook(Integer productId,Integer brokerId)
    {
        buyMap = new HashMap<Float, Integer>();
        sellMap = new HashMap<Float, Integer>();
        this.productId = productId;
        this.brokerId=brokerId;
        init=false;
    }

    /*  Adds bid to map by hashing the price, then
     *  adding bid to list located in that hash bucket
     */
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

    /*  Adds offer to map by hashing the price, then
     *  adding offer to list located in that hash bucket
     */
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
