package com.example.tradergateway.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private Integer productId;
    private String  period;
    private String  productName;

    public ProductDTO(){
    }

}
