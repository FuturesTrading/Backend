package com.example.tradergateway.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "commission", schema = "futures")
public class Commission {
    private Integer commission_id;
    private Integer trader_id;
    private Integer broker_id;
    private Integer order_id;
    private Integer percent;

    @Id
    @Column(name = "commission_id")
    public Integer getCommission_id(){return commission_id;}
    public void setCommission_id(Integer input){this.commission_id = input;}

    @Basic
    @Column(name = "trader_id")
    public Integer getTrader_id(){return  trader_id;}
    public void setTrader_id(Integer input){this.trader_id = input;}

    @Basic
    @Column(name = "broker_id")
    public Integer getBroker_id(){return  broker_id;}
    public void setBroker_id(Integer input){this.broker_id = input;}

    @Basic
    @Column(name = "order_id")
    public Integer getOrder_id(){return  order_id;}
    public void setOrder_id(Integer input){this.order_id = input;}

    @Basic
    @Column(name = "percent")
    public Integer getPercent(){return  percent;}
    public void setPercent(Integer input){this.percent = input;}
}
