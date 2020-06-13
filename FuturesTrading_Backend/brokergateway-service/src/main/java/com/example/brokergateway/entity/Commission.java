package com.example.brokergateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "commission", schema = "futures")
public class Commission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_id")
    private Integer commissionId;

    @Basic
    @Column(name = "trader_id")
    private Integer traderId;

    @Basic
    @Column(name = "broker_id")
    private Integer brokerId;

    @Basic
    @Column(name = "order_id")
    private Integer orderId;

    @Basic
    @Column(name = "percent")
    private Integer percent;

}
