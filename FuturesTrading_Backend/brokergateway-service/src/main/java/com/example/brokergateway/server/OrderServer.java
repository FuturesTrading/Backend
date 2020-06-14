package com.example.brokergateway.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.brokergateway.DAO.OrdersDAO;
import com.example.brokergateway.DAO.TradeDAO;
import com.example.brokergateway.entity.Info;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.entity.Trade;
import com.example.brokergateway.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Boolean addOne(Orders input) {
        Boolean state = ordersDAO.addOne(input);
        if(input.getVariety() == 1){
            traderClient.orderBookUpdateNew(input.getBroker_id(), input.getProduct_id(),input.getIn_or_out(),input.getPrice(), input.getIn_or_out());
        }
        if (state) {
            handleCease(input.getBroker_id(), input.getProduct_id(), input.getIn_or_out());
        }
        webSocketServer.sendMessage(input.getBroker_id() + "_" + input.getProduct_id(), getInfo(input.getBroker_id(), input.getProduct_id()));
        return true;
    }

    public String getInfo(Integer broker_id, Integer product_id) {
        List<Orders> sell = getByBroker_id(broker_id, true, product_id);
        List<Orders> buy = getByBroker_id(broker_id, false, product_id);
        List<Info> res1 = new ArrayList<>();
        Integer size = sell.size();
        Info info;
        float price = sell.get(0).getPrice();
        Integer vol = 0, loc = 0;
        while (loc < size) {
            Orders tmp = sell.get(loc);
            if (tmp.getPrice() == price) {
                vol += tmp.getQuantity();
            } else {
                info = new Info(false, vol, price);
                res1.add(info);
                vol = 0;
                price = tmp.getPrice();
            }
            loc++;
        }
        if (vol != 0) {
            info = new Info(false, vol, price);
            res1.add(info);
            vol = 0;
        }
        addLevel(res1);

        List<Info> res2 = new ArrayList<>();
        size = buy.size();
        price = 0;
        while (loc < size) {
            Orders tmp = buy.get(loc);
            if (tmp.getPrice() == price) {
                vol += tmp.getQuantity();
            } else {
                info = new Info(false, vol, price, res2.size() + 1);
                res2.add(info);
                vol = 0;
                price = tmp.getPrice();
            }
            loc++;
        }
        if (vol != 0) {
            info = new Info(false, vol, price);
            res1.add(info);
            vol = 0;
        }

        res1.addAll(res2);
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
        float trigger = market.get(0).getPrice();//买方最高价
        for (Orders a : cease) {
            if (a.getPrice() < trigger) {
                if (!handleCease1(a))
                    break;
            }
        }
    }

    public Boolean handleCease1(Orders input) {
        List<Orders> res = getByBroker_id(input.getBroker_id(), !input.getIn_or_out(), input.getProduct_id());
        Integer remain = input.getRemain();
        for (Orders a : res) {
            Integer num = a.getRemain();
            Integer business;
            if (num > remain) {
                ordersDAO.setCease(input.getOrder_id(), 2);
                business = remain;
            } else {
                ordersDAO.setElse(a.getOrder_id(), 2);
                business = num;
            }
            ordersDAO.decrease(a.getOrder_id(), business);
            traderClient.orderBookUpdateDelete(input.getBroker_id(),inpug.getProduct_id(),business,a.getPrice(),a.getIn_or_out());
            ordersDAO.decrease(input.getOrder_id(), business);
            Integer buyer_id, seller_id;
            if (input.getIn_or_out()) {
                buyer_id = input.getTrader_id();
                seller_id = a.getTrader_id();
            } else {
                seller_id = input.getTrader_id();
                buyer_id = a.getTrader_id();
            }
            Trade trade = new Trade(input.getBroker_id(),
                    buyer_id,
                    seller_id,
                    input.getProduct_id(),
                    business,
                    input.getIn_or_out());
            tradeDAO.addOne(trade);
            traderClient.addTrade(trade);
            if (business == remain)
                return true;
        }
        return false;
    }

    public void handle(Orders input, Integer type) {
        switch (type) {
            case 1://Market Order
                List<Orders> res = getByBroker_id(input.getBroker_id(), !input.getIn_or_out(), input.getProduct_id());
                Integer remain = input.getRemain();
                for (Orders a : res) {
                    Integer num = a.getRemain();
                    Integer business;
                    if (num > remain) {
                        ordersDAO.setElse(input.getOrder_id(), 2);
                        business = remain;
                    } else {
                        ordersDAO.setElse(a.getOrder_id(), 2);
                        business = num;
                    }
                    ordersDAO.decrease(a.getOrder_id(), business);
                    ordersDAO.decrease(input.getOrder_id(), business);
                    Integer buyer_id, seller_id;
                    if (input.getIn_or_out()) {
                        buyer_id = input.getTrader_id();
                        seller_id = a.getTrader_id();
                    } else {
                        seller_id = input.getTrader_id();
                        buyer_id = a.getTrader_id();
                    }
                    Trade trade = new Trade(input.getBroker_id(),
                            buyer_id,
                            seller_id,
                            input.getProduct_id(),
                            business,
                            input.getIn_or_out());
                    tradeDAO.addOne(trade);
                    if (business == remain)
                        break;
                }
                break;
            case 3://stop Order\Cease Order
                break;
            case 4://cancel Order
                Integer order_id = input.getCancel_id();
                ordersDAO.setElse(order_id, 3);
                break;
            default://limit Order\Astrict Order 不用处理啊
                break;
        }
    }


    public List<Orders> getOrdersByTraderId(Integer traderId) {
        return ordersDAO.getByTrader(traderId);
    }
}
