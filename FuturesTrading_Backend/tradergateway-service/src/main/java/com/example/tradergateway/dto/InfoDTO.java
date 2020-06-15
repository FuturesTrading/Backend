package com.example.tradergateway.dto;


import lombok.Data;

@Data
public class InfoDTO {
    private String buy_level;
    private String buy_vol;
    private String sell_level;
    private String sell_vol;
    private String price;

    public InfoDTO(Boolean mark, Integer vol, float price){
        if(mark){
            buy_vol = vol.toString();
        }else{
            sell_vol = vol.toString();
        }
        this.price = String.valueOf(price);
        buy_level = "";
        sell_level = "";
    }

    public InfoDTO(Boolean mark, Integer vol, float price, Integer level){
        if(mark){
            buy_vol = vol.toString();
        }else{
            sell_vol = vol.toString();
        }
        this.price = String.valueOf(price);
        this.buy_level = level.toString();
        sell_level = "";
    }

    public void set(Integer num){
        this.sell_level = num.toString();
    }
}
