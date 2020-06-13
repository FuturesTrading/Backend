package com.example.traderservicedemo.dao;

import com.example.traderservicedemo.model.Trader;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraderDao extends JpaRepository<Trader,Long> {

    public Trader findByTrader_nameAndAndTrader_password(String username, String password);

    public Trader findByTrader_name(String username);

    public Trader findByTrader_id(Integer Id);

}
