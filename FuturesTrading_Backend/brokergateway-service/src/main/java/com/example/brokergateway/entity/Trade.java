package com.example.brokergateway.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade", schema = "futures")
public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;

    @Basic
    @Column(name = "broker_id")
    private Integer brokerId;

    @Basic
    @Column(name = "buyer_id")
    private Integer buyerId;

    @Basic
    @Column(name = "seller_id")
    private Integer sellerId;

    @Basic
    @Column(name = "time")
    private Timestamp time;

    @Basic
    @Column(name = "product_id")
    private Integer productId;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Basic
    @Column(name = "direction")
    private Boolean direction;

    public Trade(){

    }

    public Trade(Integer broker_id,
                 Integer buyer_id,
                 Integer seller_id,
                 Integer product_id,
                 Integer quantity,
                 Boolean direction){
        this.brokerId = broker_id;
        this.buyerId = buyer_id;
        this.sellerId = seller_id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.productId = product_id;
        this.quantity = quantity;
        this.direction = direction;

    }


}
