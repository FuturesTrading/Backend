package com.example.tradergateway.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class InfoDTO implements Serializable {
    private String buy_level;
    private String buy_vol;
    private String sell_level;
    private String sell_vol;
    private String price;

    public InfoDTO( Integer vol, float price){
        sell_vol = vol.toString();
        buy_vol = "";
        this.price = String.valueOf(price);
        buy_level = "";
        sell_level = "";
    }

    public InfoDTO(Integer vol, float price,Integer level){
        buy_vol = vol.toString();
        buy_level = level.toString();
        sell_level = "";
        sell_vol = "";
        this.price = String.valueOf(price);
    }

    public void set(Integer num) {
        this.sell_level = num.toString();
    }

    InfoDTO(InfoDTO o){
        buy_level=o.getBuy_level();
        buy_vol=o.getBuy_vol();
        sell_level=o.getSell_level();
       sell_vol=o.getSell_vol();
        price=o.getPrice();
    }
    InfoDTO(){

    }
}
