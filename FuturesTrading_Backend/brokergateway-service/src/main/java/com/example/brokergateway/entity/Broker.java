package com.example.brokergateway.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
@Proxy(lazy = false)
@Data
@Entity
@Table(name = "broker", schema = "futures")
public class Broker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_id")
    private Integer brokerId;

    @Basic
    @Column(name = "broker_name")
    private String  brokerName;

    @Basic
    @Column(name = "broker_password")
    private String  brokerPassword;
}
