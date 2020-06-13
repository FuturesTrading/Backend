package com.example.tradergateway.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "trade", schema = "futures")
public class Trade {
    private Integer trade_id;
    private Integer broker_id;
    private Integer buyer_id;
    private Integer seller_id;
    private Timestamp time;
    private Integer product_id;
    private Integer quantity;
    private Boolean direction;

    public Trade(){

    }

    public Trade(Integer broker_id,
                 Integer buyer_id,
                 Integer seller_id,
                 Integer product_id,
                 Integer quantity,
                 Boolean direction){
        this.broker_id = broker_id;
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.product_id = product_id;
        this.quantity = quantity;
        this.direction = direction;

    }

    @Id
    @Column(name = "trade_id")
    public Integer getTrade_id(){return trade_id;}
    public void setTrade_id(Integer input){this.trade_id = input;}

    @Basic
    @Column(name = "broker_id")
    public Integer getBroker_id(){return  broker_id;}
    public void setBroker_id(Integer input){this.broker_id = input;}

    @Basic
    @Column(name = "buyer_id")
    public Integer getBuyer_id(){return  buyer_id;}
    public void setBuyer_id(Integer input){this.buyer_id = input;}

    @Basic
    @Column(name = "seller_id")
    public Integer getSeller_id(){return  seller_id;}
    public void setSeller_id(Integer input){this.seller_id = input;}

    @Basic
    @Column(name = "product_id")
    public Integer getProduct_id(){return  product_id;}
    public void setProduct_id(Integer input){this.product_id = input;}

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity(){return  quantity;}
    public void setQuantity(Integer input){this.quantity = input;}

    @Basic
    @Column(name = "direction")
    public Boolean getDirection(){return  direction;}
    public void setDirection(Boolean input){this.direction = input;}

    @Basic
    @Column(name = "time")
    public Timestamp getTime(){return  time;}
    public void setTime(Timestamp input){this.time = input;}


}
