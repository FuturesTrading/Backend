package com.example.brokergateway.server;

import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.DAO.ProductDAO;
import com.example.brokergateway.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServer {
    @Autowired
    public ProductDAO productDAO;

    public Product getOne(Integer product_id) {
        return productDAO.getOne(product_id);
    }
    public void addOne(Integer product_id){
        Product product = productDAO.getOne(product_id);
        if(product == null){
            product.setPeriod(String.valueOf(product_id/10));
            String name = "";
            switch (product_id%10){
                case 1:
                    name = "A";
                    break;
                case 2:
                    name = "B";
                    break;
                case 3:
                    name = "C";
                    break;
                default:
                    name = "D";
            }
            product.setProductName(name);
            productDAO.addOne(product);
        }
    }
}
