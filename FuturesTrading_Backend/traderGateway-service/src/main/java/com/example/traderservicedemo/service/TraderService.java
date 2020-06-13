package com.example.traderservicedemo.service;

import com.example.common.exception.ServiceException;
import com.example.traderservicedemo.dao.TraderDao;
import com.example.traderservicedemo.model.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.common.response.ResultCode.EXIST_ACCOUNT;
import static com.example.common.response.ResultCode.WRONG_ACCOUNTORPASSWORD;

@Service
public class TraderService {

    @Autowired
    private TraderDao traderDao;

    public void traderSignUp(Trader trader){
        String name=trader.getTrader_name();
        String pwd=trader.getTrader_password();
        if(name.equals("")||pwd.equals("")){
            throw new ServiceException(WRONG_ACCOUNTORPASSWORD);
        }
        Trader trader1=traderDao.findByTrader_name(name);
        if(!trader1.getTrader_password().equals(pwd)){
            throw new ServiceException(WRONG_ACCOUNTORPASSWORD);
        }
    }

    public void traderRegister(Trader trader) {
        String name=trader.getTrader_name();
        String pwd=trader.getTrader_password();
        if(name.equals("")||pwd.equals("")){
            throw new ServiceException(WRONG_ACCOUNTORPASSWORD);
        }
        Trader trader1=traderDao.findByTrader_name(name);
        if(trader1==null){
            traderDao.save(trader);
        }
        else{
            throw new ServiceException(EXIST_ACCOUNT);
        }
    }

}
