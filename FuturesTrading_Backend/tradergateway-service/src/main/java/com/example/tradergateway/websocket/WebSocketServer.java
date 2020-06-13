package com.example.tradergateway.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/traderSocket/orderBook/{traderId}")
public class WebSocketServer {
    /**
     * 全部在线会话
     */
    private static Map<Integer, Session> onlineSessions = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(@PathParam("traderId") Integer traderId, Session session) {
        onlineSessions.put(traderId,session);
        System.out.println(traderId);
        System.out.println(onlineSessions);
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
//       onlineSessions.remove(session.getId());
        System.out.println("close");
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给特定的人
     */
    public void sendMessage(Integer traderId, String jsonMsg){
            Session session=onlineSessions.get(traderId);
        try {
            session.getBasicRemote().sendText(jsonMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 公共方法：发送信息给所有人
     * 用于更新OrderBook
     */
    public void sendMessageToAll(String jsonMsg) {
        onlineSessions.forEach((username, session) -> {
            try {
                session.getBasicRemote().sendText(jsonMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}