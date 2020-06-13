package com.example.tradergateway.entity;


import com.example.tradergateway.dto.OrdersDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="ordertosend")
public class OrderToSend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Basic
    @Column(name = "order_id")
    private Integer orderId;

    @Basic
    @Column(name = "release_time")
    private Timestamp releaseTime;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Basic
    @Column(name = "product_id")
    private Integer productId;

    @Basic
    @Column(name = "trader_id")
    private Integer traderId;

    @Basic
    @Column(name = "broker_id")
    private Integer brokerId;

    @Basic
    @Column(name = "state")
    private Integer state;
    //state 预计分为处理中、已完成、已取消，分别对应 1、2、3

    @Basic
    @Column(name = "class")
    private Integer variety;

    @Basic
    @Column(name = "remain")
    private Integer remain;

    @Basic
    @Column(name = "price")
    private Float price;

    @Basic
    @Column(name = "in_or_out")
    private Boolean inOrOut;

    @Basic
    @Column(name = "cancel_id")
    private Integer cancelId;

    @Basic
    @Column(name = "send_time")
    private String sendTime;

    public void setOrder(OrdersDTO o){
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
    }

    public OrdersDTO getOrder(){
        OrdersDTO o=new OrdersDTO();
        o.setOrderId(orderId);
        o.setReleaseTime(releaseTime);
        o.setQuantity(quantity);
        o.setProductId(productId);
        o.setTraderId(traderId);
        o.setBrokerId(brokerId);
        o.setState(state);
        o.setVariety(variety);
        o.setInOrOut(inOrOut);
        o.setRemain(remain);
        o.setPrice(price);
        o.setCancelId(cancelId);
        return o;
    }
}
