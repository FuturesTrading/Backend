package com.example.brokergateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product", schema = "futures")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Basic
    @Column(name = "period")
    private String  period;

    @Basic
    @Column(name = "product_name")
    private String  productName;

    public Product(){

    }
}
