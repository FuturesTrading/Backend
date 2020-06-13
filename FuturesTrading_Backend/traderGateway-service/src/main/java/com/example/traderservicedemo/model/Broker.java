package com.example.traderservicedemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "broker", schema = "futures")
public class Broker {
    private Integer broker_id;
    private String  broker_name;
    private String  broker_password;

    @Id
    @Column(name = "broker_id")
    public Integer getBroker_id(){return broker_id;}
    public void  setBroker_id(Integer input){this.broker_id = input;}

    @Basic
    @Column(name = "broker_name")
    public String getBroker_name(){return  broker_name;}
    public void setBroker_name(String input){this.broker_name = input;}

    @Basic
    @Column(name = "broker_password")
    public String getBroker_password(){return  broker_password;}
    public void setBroker_password(String input){this.broker_password = input;}
}
