package com.java.mmzsblog.server;

import com.java.mmzsblog.client.TestWebSocketClient;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author created by mmzsblog.cn
 * @description 订单通知
 * @date 2021年6月1日 11:02:57
 */
@Component
@ServerEndpoint("/web_socket/order_notification/{merchantId}")
public class TestWebSocketServer {

    static final ConcurrentHashMap<String, List<TestWebSocketClient>> webSocketClientMap= new ConcurrentHashMap<>();

    /**
     * 连接建立成功时触发，绑定参数
     * @param session 与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @param merchantId 商户ID
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("merchantId") String merchantId){

        TestWebSocketClient client = new TestWebSocketClient();
        client.setSession(session);
        client.setUri(session.getRequestURI().toString());

        List<TestWebSocketClient> testWebSocketClientList = webSocketClientMap.get(merchantId);
        if(testWebSocketClientList == null){
            testWebSocketClientList = new ArrayList<>();
        }
        testWebSocketClientList.add(client);
        webSocketClientMap.put(merchantId, testWebSocketClientList);
    }

    /**
     * 连接关闭时触发，注意不能向客户端发送消息了
     * @param merchantId
     */
    @OnClose
    public void onClose(@PathParam("merchantId") String merchantId){
        webSocketClientMap.remove(merchantId);
    }

    /**
     * 通信发生错误时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     * @param merchantId
     * @param message
     */
    public static void sendMessage(String merchantId,String message){
        try {
            List<TestWebSocketClient> webSocketClientList = webSocketClientMap.get(merchantId);
            if(webSocketClientList!=null){
                for(TestWebSocketClient webSocketServer:webSocketClientList){
                    webSocketServer.getSession().getBasicRemote().sendText(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
