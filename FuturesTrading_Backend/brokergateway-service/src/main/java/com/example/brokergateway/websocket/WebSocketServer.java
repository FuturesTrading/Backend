package com.example.brokergateway.websocket;

import com.example.brokergateway.server.OrderServer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//参照https://www.cnblogs.com/jpfss/p/8777528.html
@Component
@ServerEndpoint("/socket/{input}")
public class WebSocketServer {

    public OrderServer orderServer;
    /**
     * 全部在线会话
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static Map<String, String> detail = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger("Endpoint");

    @OnOpen
    public void onOpen(@PathParam("input") String name, Session session) {
        String[] res = name.split("_");
        onlineSessions.put(res[0],session);
        detail.put(res[0],res[1]+"_"+res[2]);
        logger.log(Level.INFO, "Connection opened.");
    }


    @OnMessage
    public void onMessage(Session session, String jsonStr) {

    }

    @OnClose
    public void onClose(Session session) {
        Collection<Session> col = onlineSessions.values();
        while(true == col.contains(session)) {
            col.remove(session);
        }
        onlineSessions.remove(session.getId());
        logger.log(Level.INFO, "Connection closed.");
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        Collection<Session> col = onlineSessions.values();
        while(true == col.contains(session)) {
            col.remove(session);
        }
        onlineSessions.remove(session.getId());

        logger.log(Level.INFO, error.toString());
        logger.log(Level.INFO, "Connection error.");
    }

    /**
     * 公共方法：发送信息给特定的人
     */
    public void sendMessage(String username,String jsonMsg){
            Session session=onlineSessions.get(username);
        try {
            session.getBasicRemote().sendText(jsonMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}