package com.example.brokergateway.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "trader", schema = "futures")
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trader_id")
    private Integer traderId;

    @Basic
    @Column(name = "trader_name")
    private String  traderName;

    @Basic
    @Column(name = "trader_password")
    private String  traderPassword;

}
