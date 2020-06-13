package com.example.tradergateway.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "product", schema = "futures")
public class Product {
    private Integer product_id;
    private String  period;
    private String  product_name;

    @Id
    @Column(name = "product_id")
    public Integer getProduct_id(){return product_id;}
    public void  setProduct_id(Integer input){this.product_id = input;}

    @Basic
    @Column(name = "product_name")
    public String getProduct_name(){return  product_name;}
    public void setProduct_name(String input){this.product_name = input;}

    @Basic
    @Column(name = "period")
    public String getPeriod(){return  period;}
    public void setPeriod(String input){this.period = input;}
}
