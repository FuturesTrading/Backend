package com.example.tradergateway.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Data
public class TradeDTO {
    Integer tradeId;
    Integer brokerId;
    Integer buyerId;
    Integer sellerId;
    Timestamp time;
    Integer productId;
    Integer quantity;
    Integer direction;
    Integer commissionCount;
}
