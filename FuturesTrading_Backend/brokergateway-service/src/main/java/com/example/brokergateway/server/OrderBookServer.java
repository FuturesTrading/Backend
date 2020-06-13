package com.example.brokergateway.server;

import com.example.brokergateway.entity.Info;
import com.example.brokergateway.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBookServer {
    @Autowired
    public OrderServer orderServer;

    public List<Info> getOrderBook(Integer broker_id,Integer product_id){
        List<Orders> sell = orderServer.getByBroker_id(broker_id,true,product_id);
        List<Orders> buy = orderServer.getByBroker_id(broker_id,false,product_id);
        return remote(sell,buy);
    }


    private List<Info> remote(List<Orders> sell, List<Orders> buy){
        List<Info> res1 = new ArrayList<>();
        Integer size = sell.size();
        Info info;
        float price = sell.get(0).getPrice();
        Integer vol = 0, loc = 0;
        while(loc < size){
            Orders tmp = sell.get(loc);
            if(tmp.getPrice() == price){
                vol += tmp.getQuantity();
            }else{
                info = new Info(false,vol,price);
                res1.add(info);
                vol = 0;
                price = tmp.getPrice();
            }
            loc++;
        }
        if(vol != 0){
            info = new Info(false,vol,price);
            res1.add(info);
            vol = 0;
        }
        addLevel(res1);

        List<Info> res2 = new ArrayList<>();
        size = buy.size();
        price = 0;
        while(loc < size){
            Orders tmp = buy.get(loc);
            if(tmp.getPrice() == price){
                vol += tmp.getQuantity();
            }else{
                info = new Info(false,vol,price,res2.size()+1);
                res2.add(info);
                vol = 0;
                price = tmp.getPrice();
            }
            loc++;
        }
        if(vol != 0){
            info = new Info(false,vol,price);
            res1.add(info);
            vol = 0;
        }

        res1.addAll(res2);
        return  res1;
    }

    public void addLevel(List<Info> res){
        Integer size = res.size();
        for(Integer i = 0; i < size; i++){
            res.get(i).set(size-i);
        }
    }
}