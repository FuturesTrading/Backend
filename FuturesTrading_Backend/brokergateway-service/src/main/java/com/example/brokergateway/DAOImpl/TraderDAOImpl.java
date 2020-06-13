package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.TraderDAO;
import com.example.brokergateway.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDAOImpl implements TraderDAO {
    @Autowired
    public TraderRepository traderRepository;
}
