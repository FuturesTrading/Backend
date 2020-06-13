package com.example.tradergateway.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trader", schema = "futures")
public class Trader {
    private Integer trader_id;
    private String  trader_name;
    private String  trader_password;

    @Id
    @Column(name = "trader_id")
    public Integer getTrader_id(){return trader_id;}
    public void  setTrader_id(Integer input){this.trader_id = input;}

    @Basic
    @Column(name = "trader_name")
    public String getTrader_name(){return  trader_name;}
    public void setTrader_name(String input){this.trader_name = input;}

    @Basic
    @Column(name = "trader_password")
    public String getTrader_password(){return  trader_password;}
    public void setTrader_password(String input){this.trader_password = input;}
}
