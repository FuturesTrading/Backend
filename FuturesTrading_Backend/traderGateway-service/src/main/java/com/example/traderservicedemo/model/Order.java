package com.example.traderservicedemo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "orders", schema = "futures")
public class Order {
    private Integer order_id;
    private Time release_time;
    private Integer quantity;
    private Integer product_id;
    private Integer trader_id;
    private Integer broker_id;
    private Integer state;
    //state 预计分为处理中、已完成、已取消，分别对应 1、2、3
    private Integer variety;
    private Integer remain;
    private Float price;
    private Boolean in_or_out;
    private Integer cancel_id;

    public Order(Order o){
        order_id=o.getOrder_id();
        release_time=o.getRelease_time();
        quantity=o.getQuantity();
        product_id=o.getProduct_id();
        trader_id=o.getTrader_id();
        broker_id=o.getBroker_id();
        state=o.getState();
        variety=o.getVariety();
        in_or_out=o.getIn_or_out();
        remain=o.getRemain();
        price=o.getPrice();
        cancel_id=o.getCancel_id();
    }

    @Id
    @Column(name = "order_id")
    public Integer getOrder_id(){return order_id;}
    public void setOrder_id(Integer input){ this.order_id = input;}

    @Basic
    @Column(name = "release_time")
    public Time getRelease_time(){return  release_time;}
    public void setRelease_time(Time input){this.release_time = input;}

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer input){this.quantity = input;}

    @Basic
    @Column(name = "product_id")
    public Integer getProduct_id(){return product_id;}
    public void setProduct_id(Integer input){this.product_id = input;}

    @Basic
    @Column(name = "trader_id")
    public Integer getTrader_id(){return trader_id;}
    public void setTrader_id(Integer input){this.trader_id = input;}

    @Basic
    @Column(name = "broker_id")
    public Integer getBroker_id(){return broker_id;}
    public void setBroker_id(Integer input){this.broker_id = input;}

    @Basic
    @Column(name = "state")
    public Integer getState(){return state;}
    public void setState(Integer input){this.state = input;}

    @Basic
    @Column(name = "class")
    public Integer getVariety(){return variety;}
    public void setVariety(Integer input){this.variety = input;}

    @Basic
    @Column(name = "in_or_out")
    public Boolean getIn_or_out(){return  in_or_out;}
    public void setIn_or_out(Boolean input){this.in_or_out = input;}

    @Basic
    @Column(name = "price")
    public Float getPrice(){return price;}
    public void setPrice(Float input){this.price = input;}

    @Basic
    @Column(name = "remain")
    public Integer getRemain(){return remain;}
    public void setRemain(Integer input){this.remain = input;}

    @Basic
    @Column(name = "cancel_id")
    public Integer getCancel_id(){return cancel_id;}
    public void setCancel_id(Integer input){this.cancel_id = input;}

}

