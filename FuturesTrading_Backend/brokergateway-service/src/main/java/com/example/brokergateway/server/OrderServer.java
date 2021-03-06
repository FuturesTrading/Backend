package com.example.brokergateway.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.brokergateway.DAO.CommissionDAO;
import com.example.brokergateway.DAO.OrdersDAO;
import com.example.brokergateway.DAO.TradeDAO;
import com.example.brokergateway.entity.Info;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.entity.Trade;
import com.example.brokergateway.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServer {
    @Autowired
    public OrdersDAO ordersDAO;

    @Autowired
    public TradeDAO tradeDAO;

    @Autowired
    public WebSocketServer webSocketServer;

    @Autowired
    public TraderClient traderClient;

    @Autowired
    public CommissionDAO commissionDAO;

    public Integer addOne(Orders input) {
        Boolean state = input.getVariety() == 1;
         Integer id = ordersDAO.addOne(input);
        if(input.getVariety() == 1){
            traderClient.orderBookUpdateNew(input.getBrokerId(), input.getProductId(),input.getQuantity(),input.getPrice(), input.getInOrOut());
        }
        if (state) {
            handleCease(input.getBrokerId(), input.getProductId(), input.getInOrOut());
        }
        webSocketServer.sendMessage(input.getBrokerId() + "_" + input.getProductId(), getInfo(input.getBrokerId(), input.getProductId()));
        return id;
    }

    public String getInfo(Integer broker_id, Integer product_id) {
        List<Orders> sell = getByBroker_id(broker_id, true, product_id);
        List<Orders> buy = getByBroker_id(broker_id, false, product_id);
        List<Info> res1 = new ArrayList<>();
        Integer size = sell.size();
        Info info;
        float price = 0;
        Integer vol = 0, loc = 0;
        if(size != 0){
            price=sell.get(0).getPrice();
            while (loc < size) {
                Orders tmp = sell.get(loc);
                if (tmp.getPrice() == price) {
                    vol += tmp.getRemain();
                } else {
                    info = new Info( vol, price);
                    res1.add(info);
                    vol = tmp.getRemain();
                    price = tmp.getPrice();
                }
                loc++;
            }
            if (vol != 0) {
                info = new Info( vol, price);
                res1.add(info);
                vol = 0;
            }
            addLevel(res1);
        }
        List<Info> res2 = new ArrayList<>();
        size = buy.size();
        loc=0;
        if(size != 0) {
            price = buy.get(0).getPrice();
            while (loc < size) {
                Orders tmp = buy.get(loc);
                if (tmp.getPrice() == price) {
                    vol += tmp.getRemain();
                } else {
                    info = new Info( vol, price, res2.size() + 1);
                    res2.add(info);
                    vol = tmp.getRemain();
                    price = tmp.getPrice();
                }
                loc++;
            }
            if (vol != 0) {
                info = new Info( vol, price,res2.size()+1);
                res2.add(info);
                vol = 0;
            }
        }
        res1.addAll(res2);
        System.out.println(res2);
        System.out.println(res1);
        return String.valueOf(JSONArray.parseArray(JSON.toJSONString(res1)));
    }

    public void addLevel(List<Info> res) {
        Integer size = res.size();
        for (Integer i = 0; i < size; i++) {
            res.get(i).set(size - i);
        }
    }

    public List<Orders> getByBroker_id(Integer broker_id, Boolean in_or_out, Integer product_id) {
        return ordersDAO.getByBroker(broker_id, in_or_out, product_id);
    }

    public List<Orders> getByBroker_id(Integer broker_id) {
        return ordersDAO.getByBroker(broker_id);
    }

    public List<Orders> getByBroker_id_handled(Integer broker_id) {
        return ordersDAO.getByBroker_handled(broker_id);
    }

    public void handleCease(Integer broker_id, Integer product_id, Boolean in_or_out) {
        List<Orders> market = getByBroker_id(broker_id, false, product_id);
        List<Orders> cease = ordersDAO.getCease(broker_id, product_id, in_or_out);
        if(market==null || market.size()==0) return;
        float trigger = market.get(0).getPrice();//买方最高价
        for (Orders a : cease) {
            if (a.getPrice() < trigger) {
                if (!handleCease1(a))
                    break;
            }
        }
    }

    public Boolean handleCease1(Orders input) {
        List<Orders> res = getByBroker_id(input.getBrokerId(), !input.getInOrOut(), input.getProductId());
        if(!input.getInOrOut()) Collections.reverse(res);
        Integer remain = input.getRemain();
        for (Orders a : res) {
            Integer num = a.getRemain();
            Integer business;
            if (num > remain) {
                ordersDAO.setCease(input.getOrderId(), 2);
                business = remain;
            } else {
                ordersDAO.setElse(a.getOrderId(), 2);
                business = num;
            }
            ordersDAO.decrease(a.getOrderId(), business);
            traderClient.orderBookUpdateDelete(input.getBrokerId(),input.getProductId(),business,a.getPrice(),a.getInOrOut());
            ordersDAO.decrease(input.getOrderId(), business);
            Integer buyer_id, seller_id;
            if (input.getInOrOut()) {
                buyer_id = input.getTraderId();
                seller_id = a.getTraderId();
            } else {
                seller_id = input.getTraderId();
                buyer_id = a.getTraderId();
            }
            Trade trade = new Trade(
                    input.getBrokerId(),
                    buyer_id,
                    seller_id,
                    input.getProductId(),
                    business,
                    input.getInOrOut(),
                    commissionDAO.getOne(input.getBrokerId(),input.getProductId()),
                    a.getPrice());
            tradeDAO.addOne(trade);
            traderClient.addTrade(trade);
            if (business == remain){
                webSocketServer.sendMessage(input.getBrokerId() + "_" + input.getProductId(), getInfo(input.getBrokerId(), input.getProductId()));
                return true;
            }
        }

        webSocketServer.sendMessage(input.getBrokerId() + "_" + input.getProductId(), getInfo(input.getBrokerId(), input.getProductId()));

        return false;
    }

    public void handle(Orders input, Integer type) {
        System.out.println("=====handle============================================");
        System.out.println(input.toString());
        switch (type) {
            case 1://Market Order
                List<Orders> res = getByBroker_id(input.getBrokerId(), !input.getInOrOut(), input.getProductId());
                if(!input.getInOrOut()) Collections.reverse(res);
                System.out.println("========================res======================");
                System.out.println(res.toString());
                Integer remain = input.getRemain();
                for (Orders a : res) {
                    Integer num = a.getRemain();
                    Integer business;
                    if (num > remain) {
                        System.out.println(">>>>>");
                        ordersDAO.setElse(input.getOrderId(), 2);
                        business = remain;
                    } else if(num.equals(remain)){
                        System.out.println("=====");
                        ordersDAO.setElse(a.getOrderId(), 2);
                        ordersDAO.setElse(input.getOrderId(), 2);
                        business = num;
                    }
                    else{
                        System.out.println("<<<<<");
                        ordersDAO.setElse(a.getOrderId(), 2);
                        business = num;
                    }
                    ordersDAO.decrease(a.getOrderId(), business);
                    ordersDAO.decrease(input.getOrderId(), business);
                    Integer buyer_id, seller_id;
                    if (input.getInOrOut()) {
                        buyer_id = input.getTraderId();
                        seller_id = a.getTraderId();
                    } else {
                        seller_id = input.getTraderId();
                        buyer_id = a.getTraderId();
                    }
                    Trade trade = new Trade(input.getBrokerId(),
                            buyer_id,
                            seller_id,
                            input.getProductId(),
                            business,
                            input.getInOrOut(),
                            commissionDAO.getOne(input.getBrokerId(),input.getProductId()),
                            a.getPrice());
                    tradeDAO.addOne(trade);
                    if (business.equals(remain)){
                        System.out.println("========================处理结束=============");
                        webSocketServer.sendMessage(input.getBrokerId() + "_" + input.getProductId(), getInfo(input.getBrokerId(), input.getProductId()));
                        break;
                }
                    else{
                        remain=remain-business;
                    }
                }

                break;
            case 3://stop Order\Cease Order
                break;
            case 4://cancel Order
                Integer order_id = input.getCancelId();
                ordersDAO.setElse(order_id, 3);
                break;
            default://limit Order\Astrict Order 不用处理啊
                break;
        }
        webSocketServer.sendMessage(input.getBrokerId() + "_" + input.getProductId(), getInfo(input.getBrokerId(), input.getProductId()));
    }


    public List<Orders> getOrdersByTraderId(Integer traderId) {
        return ordersDAO.getByTrader(traderId);
    }
}
