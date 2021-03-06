package com.example.brokergateway.DAOImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.brokergateway.DAO.OrdersDAO;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.redis.RedisUtil;
import com.example.brokergateway.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrdersDAOImpl implements OrdersDAO {
    @Autowired
    public OrdersRepository ordersRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Integer addOne(Orders input) {
        input.setOrderId(0);
        input.setState(1);
        input.setRemain(input.getQuantity());
        if(input.getVariety()!=4) input.setCancelId(null);
        ordersRepository.save(input);
        Orders orders=ordersRepository.save(input);
        if (input.getVariety() == 3) {
            addCease(input);
        }
        return orders.getOrderId();
    }

    @Override
    public Orders getOne(Integer input) {
        Orders orders;
        Object p = redisUtil.get("Orders" + input);
        if (p == null) {
            orders = ordersRepository.getOne(input);
        } else {
            orders = (Orders) JSONObject.parse(p.toString());
        }
        return orders;
    }

    @Override
    public List<Orders> getByBroker(Integer broker_id, Boolean in_or_out, Integer product_id) {
        List<Orders> orders;
        String mark = in_or_out ? "sell " : "buy ";
        orders = ordersRepository.findByBroker_idAndStateAndVarietyAndProduct_idAndIN(broker_id, 1, 2, product_id, in_or_out);
        List<Orders> res=null;
        if(in_or_out) res=sortSell(orders);
        else{
            res=sortBuy(orders);
        }
        System.out.print(res.toString());
        redisUtil.set("Orders "+mark + product_id + " broker " + broker_id, JSONArray.toJSONString(res));
        return res;
    }

    @Override
    public List<Orders> getByBroker_handled(Integer broker_id, Boolean in_or_out, Integer product_id) {
        List<Orders> orders;
        String mark = in_or_out ? "sell " : "buy ";
        Object p = redisUtil.get("Orders " + mark + product_id + " broker " + broker_id);
        if (p == null || p.toString().equals("[]")) {
            orders = ordersRepository.findByBroker_idAndStateAndVarietyAndProduct_idAndIN
                    (broker_id, 1, 1, product_id, in_or_out);
            List<Orders> res = sort(orders);
            redisUtil.set("Orders "+mark + product_id + " broker " + broker_id, JSONArray.toJSONString(res));
            return res;
        } else {
            orders = JSONArray.parseArray(p.toString(), Orders.class);
            return orders;
        }
    }

    @Override
    public List<Orders> getByBroker(Integer input) {
        return ordersRepository.findByBroker_idAndState(input, 1);
    }

    @Override
    public List<Orders> getByBroker_handled(Integer input) {
        return ordersRepository.findByBroker_idAndState(input, 2);
    }

    private List<Orders> sort(List<Orders> orders) {
        Collections.sort(orders, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                float dif = 0;
                if(o1.getPrice()!=null && o2.getPrice()!=null)
                    dif=o1.getPrice() - o2.getPrice();
                if (dif > 0)
                    return -1;
                else if (dif < 0)
                    return 1;
                else {
                    long timedif=o1.getReleaseTime().getTime()-o2.getReleaseTime().getTime();
                    if(timedif>0) return 1;
                    if(timedif<0) return -1;
                    return 0;
                }
            }
        });
        return orders;
    }
    private List<Orders> sortBuy(List<Orders> orders) {
        Collections.sort(orders, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                float dif = 0;
                if(o1.getPrice()!=null && o2.getPrice()!=null)
                    dif=o1.getPrice() - o2.getPrice();
                if (dif > 0)
                    return -1;
                else if (dif < 0)
                    return 1;
                else {
                    long timedif=o1.getReleaseTime().getTime()-o2.getReleaseTime().getTime();
                    if(timedif>0) return 1;
                    if(timedif<0) return -1;
                    return 0;
                }
            }
        });
        return orders;
    }
    private List<Orders> sortSell(List<Orders> orders) {
        Collections.sort(orders, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                float dif = 0;
                if(o1.getPrice()!=null && o2.getPrice()!=null)
                    dif=o1.getPrice() - o2.getPrice();
                if (dif > 0)
                    return -1;
                else if (dif < 0)
                    return 1;
                else {
                    long timedif=o1.getReleaseTime().getTime()-o2.getReleaseTime().getTime();
                    if(timedif>0) return -1;
                    if(timedif<0) return 1;
                    return 0;
                }
            }
        });
        return orders;
    }

    @Override
    public Boolean setCease(Integer order_id, Integer state) {
        Orders input = getOne(order_id);
        Integer broker_id = input.getBrokerId();
        Boolean in_or_out = input.getInOrOut();
        String mark = in_or_out ? "sell " : "buy ";
        Integer product_id = input.getProductId();
        Object p = redisUtil.get("Cease " + mark + product_id + " broker " + broker_id);
        if (p != null) {
            List<Orders> orders = ordersRepository.findAllByBroker_id(broker_id);
            orders.remove(input);
            redisUtil.set("Cease"+mark + product_id + " broker " + broker_id, JSONArray.toJSONString(orders));
        }
        input.setState(state);
        ordersRepository.saveAndFlush(input);
        return true;
    }

    @Override
    public Boolean setElse(Integer order_id, Integer state) {
        Orders input = getOne(order_id);
        if (input.getVariety() == 2) {
            Integer broker_id = input.getBrokerId();
            Boolean in_or_out = input.getInOrOut();
            String mark = in_or_out ? "sell " : "buy ";
            Integer product_id = input.getProductId();
            Object p = redisUtil.get("Orders " + mark + product_id + " broker " + broker_id);
            if (p != null || !p.toString().equals("[]")) {
                List<Orders> orders = ordersRepository.findAllByBroker_id(broker_id);
                orders.remove(input);
                redisUtil.set("Orders" +mark+ product_id + " broker " + broker_id, JSONArray.toJSONString(orders));
            }
        }
        input.setState(state);
        ordersRepository.saveAndFlush(input);
        redisUtil.del("Orders" + input.getOrderId());
        return true;
    }

    @Override
    public Boolean decrease(Integer order_id, Integer amount) {
        Orders input = getOne(order_id);
        if (input.getVariety() > 2)
            return false;
        Integer remian = input.getRemain();
        if (amount > remian)
            return false;
        input.setRemain(remian - amount);
        if (input.getRemain() == 0) {
            input.setState(2);
        }
        ordersRepository.saveAndFlush(input);
        Integer broker_id = input.getBrokerId();
        Boolean in_or_out = input.getInOrOut();
        String mark = in_or_out ? "sell " : "buy ";
        Integer product_id = input.getProductId();
        Object p = redisUtil.get("Orders " + mark + product_id + " broker " + broker_id);
        if (p != null) {
            redisUtil.del("Orders " + mark + product_id + " broker " + broker_id);
        }
        return true;
    }

    @Override
    public Boolean addCease(Orders orders) {
        Integer broker_id = orders.getBrokerId();
        Integer product_id = orders.getProductId();
        String mark = orders.getInOrOut() ? "sell " : "buy ";
        Object p = redisUtil.get("Cease " + mark + product_id + " broker " + broker_id);
        if (p != null) {
            redisUtil.del("Cease " + mark + product_id + " broker " + broker_id);
        }
        List<Orders> res = ordersRepository.findByBroker_idAndStateAndVarietyAndProduct_idAndIN
                (broker_id, 1, 3, product_id, orders.getInOrOut());
        res = sort(res);
        redisUtil.set("Cease " + mark + product_id + " broker " + broker_id, res);
        return true;
    }

    @Override
    public List<Orders> getCease(Integer broker_id, Integer product_id, Boolean in_or_out) {
        String mark = in_or_out ? "sell " : "buy ";
        Object p = redisUtil.get("Cease " + mark + product_id + " broker " + broker_id);
        List<Orders> res;
        res = ordersRepository.findByBroker_idAndStateAndVarietyAndProduct_idAndIN
                    (broker_id, 1, 3, product_id, in_or_out);
        res = sort(res);
        redisUtil.set("Cease " + mark + product_id + " broker " + broker_id, res);
        return res;
    }


    @Override
    public List<Orders> getByTrader(Integer traderId) {
        return ordersRepository.findByTraderId(traderId);
    }
}
