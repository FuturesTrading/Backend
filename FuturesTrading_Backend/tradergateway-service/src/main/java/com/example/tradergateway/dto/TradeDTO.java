package com.example.tradergateway.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Data
public class TradeDTO {
    private Integer tradeId;
    private Integer brokerId;
    private Integer buyerId;
    private Integer sellerId;
    private Timestamp time;
    private Integer productId;
    private Integer quantity;
    private Boolean direction;
    private Float commissionCount;
    private Float price;
}
