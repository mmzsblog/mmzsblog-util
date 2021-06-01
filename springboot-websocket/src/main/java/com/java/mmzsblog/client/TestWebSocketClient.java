package com.java.mmzsblog.client;

import lombok.Data;

import javax.websocket.Session;


/**
 * @author ：created by mmzsblog.cn
 * @description：WebSocket客户端连接
 * @date ：created at 2021/06/01 11:04
 */
@Data
public class TestWebSocketClient {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接的uri
     */
    private String uri;

}