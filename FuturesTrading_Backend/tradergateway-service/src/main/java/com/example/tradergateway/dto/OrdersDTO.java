package com.example.tradergateway.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrdersDTO {

    private Integer orderId;

    private Timestamp releaseTime;

    private Integer quantity;

    private Integer productId;

    private Integer traderId;

    private Integer brokerId;

    private Integer state;
    //state 预计分为处理中、已完成、已取消，分别对应 1、2、3

    private Integer variety;

    private Integer remain;

    private Float price;

    private Boolean inOrOut;

    private Integer cancelId;

    private String  period;

    private String  productName;

    public OrdersDTO() {

    }
    public OrdersDTO(OrdersDTO o){
        orderId=o.getOrderId();
        releaseTime=o.getReleaseTime();
        quantity=o.getQuantity();
        productId=o.getProductId();
        traderId=o.getTraderId();
        brokerId=o.getBrokerId();
        state=o.getState();
        variety=o.getVariety();
        inOrOut=o.getInOrOut();
        remain=o.getRemain();
        price=o.getPrice();
        cancelId=o.getCancelId();
        productName=o.getProductName();
        period=o.getPeriod();
    }
}
