package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.ProductDAO;
import com.example.brokergateway.entity.Product;
import com.example.brokergateway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    public ProductRepository productRepository;

    @Override
    public Product getOne(Integer input) {
        return productRepository.getOne(input);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean addOne(Product input) {
        productRepository.save(input);
        return true;
    }
}
