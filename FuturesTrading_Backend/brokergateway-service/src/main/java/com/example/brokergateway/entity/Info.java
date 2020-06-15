package com.example.brokergateway.entity;


import lombok.Data;

@Data
public class Info {
    private String buy_level;
    private String buy_vol;
    private String sell_level;
    private String sell_vol;
    private String price;

    public Info( Integer vol, float price){
        sell_vol = vol.toString();
        buy_vol = "";
        this.price = String.valueOf(price);
        buy_level = "";
        sell_level = "";
    }

    public Info(Integer vol, float price,Integer level){
        buy_vol = vol.toString();
        buy_level = level.toString();
        sell_level = "";
        sell_vol = "";
        this.price = String.valueOf(price);
    }

    public void set(Integer num){
        this.sell_level = num.toString();
    }
}
