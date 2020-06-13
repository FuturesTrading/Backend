package com.example.traderservicedemo.model;

import lombok.Data;

@Data
public class OrderBlotter {

    private String id;
    private int count;
    private int price;
    private String creationTime;
    private String buyerTraderName;
    private String sellerTraderName;
    private String buyerOrderId;
    private String sellerOrderId;
    private String marketDepthId;
    private String futureId;

}
