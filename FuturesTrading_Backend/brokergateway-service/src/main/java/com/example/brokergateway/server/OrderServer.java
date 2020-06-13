package com.example.brokergateway.server;

import com.example.brokergateway.DAO.OrdersDAO;
import com.example.brokergateway.DAO.TradeDAO;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServer {
    @Autowired
    public OrdersDAO ordersDAO;

    @Autowired
    public TradeDAO tradeDAO;

    public Boolean addOne(Orders input) {
        Boolean state = ordersDAO.addOne(input);
        if(state){
            handleCease(input.getBrokerId(),input.getProductId(),input.getInOrOut());
        }
        return true;
    }


    public List<Orders> getByBroker_id(Integer broker_id,Boolean in_or_out,Integer product_id) {
        return ordersDAO.getByBroker(broker_id,in_or_out,product_id);
    }

    public void handleCease(Integer broker_id,Integer product_id, Boolean in_or_out){
        List<Orders> market = getByBroker_id(broker_id,false,product_id);
        List<Orders> cease =  ordersDAO.getCease(broker_id,product_id,in_or_out);
        float trigger = market.get(0).getPrice();//买方最高价
        for(Orders a:cease){
            if(a.getPrice() < trigger){
                if(!handleCease1(a))
                    break;
            }
        }
    }

    public Boolean handleCease1(Orders input) {
        List<Orders> res = getByBroker_id(input.getBrokerId(),!input.getInOrOut(),input.getProductId());
        Integer remain = input.getRemain();
        for(Orders a:res){
            Integer num = a.getRemain();
            Integer business;
            if(num > remain ){
                ordersDAO.setCease(input.getOrderId(),2);
                business = remain;
            }else{
                ordersDAO.setElse(a.getOrderId(),2);
                business = num;
            }
            ordersDAO.decrease(a.getOrderId(),business);
            ordersDAO.decrease(input.getOrderId(),business);
            Integer buyer_id,seller_id;
            if(input.getInOrOut()){
                buyer_id = input.getTraderId();
                seller_id = a.getTraderId();
            }else {
                seller_id = input.getTraderId();
                buyer_id = a.getTraderId();
            }
            Trade trade = new Trade(input.getBrokerId(),
                    buyer_id,
                    seller_id,
                    input.getProductId(),
                    business,
                    input.getInOrOut());
            tradeDAO.addOne(trade);
            if(business == remain)
                return true;
        }
        return false;
    }

    public void handle(Orders input, Integer type) {
        switch (type){
            case 1://Market Order
                List<Orders> res = getByBroker_id(input.getBrokerId(),!input.getInOrOut(),input.getProductId());
                Integer remain = input.getRemain();
                for(Orders a:res){
                    Integer num = a.getRemain();
                    Integer business;
                    if(num > remain ){
                        ordersDAO.setElse(input.getOrderId(),2);
                        business = remain;
                    }else{
                        ordersDAO.setElse(a.getOrderId(),2);
                        business = num;
                    }
                    ordersDAO.decrease(a.getOrderId(),business);
                    ordersDAO.decrease(input.getOrderId(),business);
                    Integer buyer_id,seller_id;
                    if(input.getInOrOut()){
                        buyer_id = input.getTraderId();
                        seller_id = a.getTraderId();
                    }else {
                        seller_id = input.getTraderId();
                        buyer_id = a.getTraderId();
                    }
                    Trade trade = new Trade(input.getBrokerId(),
                            buyer_id,
                            seller_id,
                            input.getProductId(),
                            business,
                            input.getInOrOut());
                    tradeDAO.addOne(trade);
                    if(business == remain)
                        break;
                }
                break;
            case 3://stop Order\Cease Order
                break;
            case 4://cancel Order
                Integer order_id = input.getCancelId();
                ordersDAO.setElse(order_id,3);
                break;
            default://limit Order\Astrict Order 不用处理啊
                break;
        }
    }

    public List<Orders> getByTraderId(Integer traderId) {
        return ordersDAO.getByTrader(traderId);
    }

    public List<Orders> getByProductId(boolean in_or_out, Integer productId) {
        return ordersDAO.getByProduct(in_or_out,productId);
    }
}
