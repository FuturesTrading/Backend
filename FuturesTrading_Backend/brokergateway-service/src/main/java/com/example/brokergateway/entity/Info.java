package com.example.brokergateway.entity;


import lombok.Data;

@Data
public class Info {
    private String buy_level;
    private String buy_vol;
    private String sell_level;
    private String sell_vol;
    private String price;

    public Info(Boolean mark, Integer vol, float price){
        if(mark){
            buy_vol = vol.toString();
        }else{
            sell_vol = vol.toString();
        }
        this.price = String.valueOf(price);
        buy_level = "";
        sell_level = "";
    }

    public Info(Boolean mark, Integer vol, float price,Integer level){
        if(mark){
            buy_vol = vol.toString();
            this.buy_level = level.toString();
        }else{
            sell_vol = vol.toString();
            this.sell_level= level.toString();
        }
        this.price = String.valueOf(price);
    }

    public void set(Integer num){
        this.sell_level = num.toString();
    }
}
