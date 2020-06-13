package com.example.traderservicedemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="orderToSend")
public class OrderToSend {
    @Id
    public String id;
    public Order order;
    public String datetime;
}
