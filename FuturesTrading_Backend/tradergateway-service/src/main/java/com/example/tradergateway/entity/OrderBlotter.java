package com.example.tradergateway.entity;

import com.example.tradergateway.dto.TradeDTO;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderBlotter {
    private static OrderBlotter orderBlotter=null;
    private Map<Integer, List<TradeDTO>> tradeDTOMap;
    private OrderBlotter(){
        tradeDTOMap=new HashMap<>();
    }

    public static synchronized OrderBlotter getInstance(){
        //判断存储实例的变量是否有值
        if(orderBlotter == null){
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            orderBlotter = new OrderBlotter();
        }
        //如果有值，那就直接使用
        return orderBlotter;
    }
}
