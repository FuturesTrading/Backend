package com.example.tradergateway.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trader")
@Data
public class Trader implements Serializable {
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
