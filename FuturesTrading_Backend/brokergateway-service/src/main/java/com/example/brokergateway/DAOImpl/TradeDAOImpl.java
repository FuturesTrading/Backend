package com.example.brokergateway.DAOImpl;

import com.example.brokergateway.DAO.TradeDAO;
import com.example.brokergateway.entity.Trade;
import com.example.brokergateway.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TradeDAOImpl implements TradeDAO {
    @Autowired
    public TradeRepository tradeRepository;

    @Override
    public Boolean addOne(Trade input) {
        input.setTradeId(0);
        System.out.println("trade save=======================================");
        tradeRepository.save(input);
        return true;
    }

    @Override
    public Trade getOne(Integer trade_id) {
        return tradeRepository.getOne(trade_id);
    }

    @Override
    public List<Trade> getByBroker_id(Integer broker_id) {
        return tradeRepository.getAllByBrokerId(broker_id);
    }

    @Override
    public List<Trade> getByTrader_id(Integer trader_id) {
        List<Trade> trades=new ArrayList<>();
        trades.addAll(tradeRepository.getAllByBuyerId(trader_id));
        trades.addAll(tradeRepository.getAllBySellerId(trader_id));
        return trades;
    }
}
