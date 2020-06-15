package com.example.tradergateway.service;

import com.example.demo.exception.ServiceException;
import com.example.demo.response.ResultCode;
import com.example.tradergateway.dao.TraderDao;
import com.example.tradergateway.entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.response.ResultCode.EXIST_ACCOUNT;
import static com.example.demo.response.ResultCode.WRONG_ACCOUNTORPASSWORD;


@Service
public class TraderService {

    @Autowired
    private TraderDao traderDao;

    public Integer traderSignUp(Trader trader){
        String name=trader.getTraderName();
        String pwd=trader.getTraderPassword();
        if(name.equals("")||pwd.equals("")){
            throw new ServiceException(ResultCode.WRONG_ACCOUNTORPASSWORD);
        }
        List<Trader> traders=traderDao.getByTraderName(name);
        if(traders==null){
            throw new ServiceException(ResultCode.WRONG_ACCOUNTORPASSWORD);
        }
        Trader trader1=traders.get(0);
        if(!trader1.getTraderPassword().equals(pwd)){
            throw new ServiceException(ResultCode.WRONG_ACCOUNTORPASSWORD);
        }
        return trader1.getTraderId();
    }

    public Integer traderRegister(Trader trader) {
        String name=trader.getTraderName();
        String pwd=trader.getTraderPassword();
        if(name.equals("")||pwd.equals("")){
            throw new ServiceException(ResultCode.WRONG_ACCOUNTORPASSWORD);
        }
        List<Trader> traders=traderDao.getByTraderName(name);
        if( traders==null || traders.size()==0){
            Trader trader1=traderDao.save(trader);
            return trader1.getTraderId();
        }
        else{
            throw new ServiceException(ResultCode.EXIST_ACCOUNT);
        }
    }

}
