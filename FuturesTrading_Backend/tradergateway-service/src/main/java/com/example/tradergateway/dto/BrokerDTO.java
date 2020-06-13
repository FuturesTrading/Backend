package com.example.tradergateway.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class BrokerDTO implements Serializable {
    private Integer brokerId;

    private String  brokerName;

    private String  brokerPassword;
}
