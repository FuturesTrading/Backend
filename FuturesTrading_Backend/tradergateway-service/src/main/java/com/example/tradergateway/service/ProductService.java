package com.example.tradergateway.service;

import com.example.tradergateway.dao.ProductDao;
import com.example.tradergateway.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProduct() {
        return productDao.findAll();
    }
    public Product getProductByProductId(Integer id){
        return productDao.findByProductId(id);
    }

    public Integer addProduct(Product product){
        List<Product> products=productDao.findByProductNameAndPeriod(product.getProductName(),product.getPeriod());
        if(products!=null&&products.size()!=0){
            return products.get(0).getProductId();
        }
        else{
            Product product1=productDao.save(product);
            return product1.getProductId();
        }
    }
}
