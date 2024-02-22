package com.heisyenberg.springchatclient.websocketclients;

import com.heisyenberg.springchatclient.controllers.ChatRoomsController;
import com.heisyenberg.springchatclient.models.ChatRoom;
import javafx.application.Platform;
import lombok.NoArgsConstructor;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

@NoArgsConstructor
public class ChatRoomsWebSocketClient {
    protected WebSocketStompClient stompClient;
    protected StompSession stompSession;
    private ChatRoomsController controller;

    public ChatRoomsWebSocketClient(ChatRoomsController controller) {
        this.controller = controller;
    }

    public void connect() {
        setStompClient();
        StompSessionHandlerAdapter sessionHandler =
                new StompSessionHandlerAdapter() {
                    @Override
                    public void afterConnected(StompSession session,
                                               StompHeaders connectedHeaders) {
                        System.out.println("Connected to WebSocket server");
                        stompSession = session;
                        session.subscribe("/topic/room/create",
                                new FrameHandler());
                    }
                };
        stompClient.connect("ws://localhost:8080/ws", sessionHandler);
    }

    protected void setStompClient() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    public void disconnect() {
        if (stompClient != null) {
            stompClient.stop();
            System.out.println("Disconnected from WebSocket server");
        }
    }

    public void send(String destination, Object payload) {
        if (stompSession != null) {
            stompSession.send(destination, payload);
        }
    }

    private class FrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return ChatRoom.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            Platform.runLater(controller::getRooms);
        }
    }
}
