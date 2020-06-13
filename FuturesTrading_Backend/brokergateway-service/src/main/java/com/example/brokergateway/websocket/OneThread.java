package com.example.brokergateway.websocket;
import com.alibaba.fastjson.JSONArray;
import com.example.brokergateway.entity.Info;
import com.example.brokergateway.entity.Orders;
import com.example.brokergateway.server.OrderBookServer;
import com.example.brokergateway.server.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OneThread extends Thread {

    @Autowired
    public OrderServer orderServer;
    private OrderBookServer orderBookServer;
    private Session session;
    private Integer broker_id;
    private Integer product_id;

    public OneThread(Session session,Integer broker_id,Integer product_id) {
        this.session = session;
        this.broker_id = broker_id;//此时是0条消息
        this.product_id = product_id;
    }

    @Override
    public void run() {
        while (true) {
            List<Info> res=orderBookServer.getOrderBook(broker_id,product_id);
            try {
                session.getBasicRemote().sendText(JSONArray.toJSONString(res)); //No encoder specified for object of class [class AlarmMessage]
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //一秒刷新一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}