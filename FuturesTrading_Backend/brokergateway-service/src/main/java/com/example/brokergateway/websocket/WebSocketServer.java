package com.example.brokergateway.websocket;

import com.example.brokergateway.server.OrderServer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint("/socket/{input}")
public class WebSocketServer {

    public OrderServer orderServer;
    /**
     * 全部在线会话
     */
    private static Map<String, List<Session>> onlineSessions = new ConcurrentHashMap<>();
    private static Map<String, String> detail = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger("Endpoint");

    @OnOpen
    public void onOpen(@PathParam("input") String name, Session session) {
        String[] res = name.split("_");
        List<Session> tmp = onlineSessions.get(name);
        if(tmp == null) {
            tmp = new ArrayList<>();
        }
        tmp.add(session);
        onlineSessions.put(name,tmp);
        detail.put(session.getId(),name);
        logger.log(Level.INFO, "Connection opened.");
    }


    @OnMessage
    public void onMessage(Session session, String jsonStr) {

    }

    @OnClose
    public void onClose(Session session) {
        String name = detail.get(session.getId());
        List<Session> res = onlineSessions.get(name);
        res.remove(session);
        onlineSessions.put(name,res);
        detail.remove(session.getId());
        logger.log(Level.INFO, "Connection closed.");
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String name = detail.get(session.getId());
        List<Session> res = onlineSessions.get(name);
        res.remove(session);
        onlineSessions.put(name,res);

        logger.log(Level.INFO, error.toString());
        logger.log(Level.INFO, "Connection error.");
    }

    /**
     * 公共方法：发送信息给特定的人
     */
    public void sendMessage(String name,String jsonMsg){
        List<Session> session=onlineSessions.get(name);
        if(session==null) return;
        try {
            for(Session a:session){
                a.getBasicRemote().sendText(jsonMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}