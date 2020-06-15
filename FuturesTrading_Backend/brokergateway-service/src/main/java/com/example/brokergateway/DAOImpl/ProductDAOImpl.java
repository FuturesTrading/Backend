package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.ProductDAO;
import com.example.brokergateway.entity.Commission;
import com.example.brokergateway.entity.Product;
import com.example.brokergateway.repository.CommissionRepository;
import com.example.brokergateway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CommissionRepository commissionRepository;

    @Override
    public Product getOne(Integer input) {
        return productRepository.getOne(input);
    }

    @Override
    public List<Product> getAll(Integer broker_id) {
        List<Commission> commissions=commissionRepository.getAll(broker_id);
        List<Product> products=new ArrayList<>();
        for(Commission commission:commissions){
            products.add(productRepository.getOne(commission.getBrokerId()));
        }
        return products;
    }

    @Override
    public Boolean addOne(Product input) {
        productRepository.save(input);
        return true;
    }

}
