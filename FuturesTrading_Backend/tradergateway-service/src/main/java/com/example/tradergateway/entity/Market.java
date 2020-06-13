package com.example.tradergateway.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Data
public class Market {
    private static Market market=null;
    private Map<String,OrderBook> marketMap;
    private Market(){
        marketMap=new HashMap<>();
    }

    public static synchronized Market getInstance(){
        //判断存储实例的变量是否有值
        if(market == null){
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            market = new Market();
        }
        //如果有值，那就直接使用
        return market;
    }
}
