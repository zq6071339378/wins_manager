package com.datacomo.wins.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Socket处理器
 * Created by duanlinzhuo on 2016/5/18.
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static Logger logger = Logger.getLogger(MyWebSocketHandler.class.getName());
    public static final Map<Integer, WebSocketSession> userMap ;
    static {
        userMap = new HashMap<Integer, WebSocketSession>();
    }

    /**
     * 建立连接后
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("afterConnectionEstablished method start");
        int userId = (int) session.getAttributes().get("userId");
        if (userMap.get(userId) == null) {
            userMap.put(userId,session);
        }
        logger.info("afterConnectionEstablished method end");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    /**
     *消息传输错误处理
     * @param session
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.info("handleTransportError method start");
        if (session.isOpen()) {
            session.close();
        }
        Iterator<Map.Entry<Integer, WebSocketSession>> it = userMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<Integer, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                logger.debug("websocket connection error......");
                break;
            }
        }
        logger.info("handleTransportError method end");
    }

    /**
     * 关闭连接后
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("afterConnectionClosed method start");
        System.out.println("Websocket---" + session.getId() + ":已经关闭");
        Iterator<Map.Entry<Integer, WebSocketSession>> it = userMap
                .entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<Integer, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID---" + entry.getKey());
                logger.debug("websocket connection closed......");
                break;
            }
        }
        logger.info("afterConnectionClosed method end");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
        logger.info("broadcast method start");
        Iterator<Map.Entry<Integer, WebSocketSession>> it = userMap.entrySet().iterator();
        // 多线程群发
        while (it.hasNext()) {
            final Map.Entry<Integer, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            entry.getValue().sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        }
        logger.info("broadcast method end");
    }

}
