package com.heisyenberg.springchatclient.websocketclients;

import com.heisyenberg.springchatclient.controllers.MessagesController;
import com.heisyenberg.springchatclient.models.Message;
import javafx.application.Platform;
import lombok.NoArgsConstructor;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@NoArgsConstructor
public class MessagesWebSocketClient extends ChatRoomsWebSocketClient {
    private MessagesController controller;

    public MessagesWebSocketClient(MessagesController controller) {
        this.controller = controller;
    }

    @Override
    public void connect() {
        super.setStompClient();
        StompSessionHandlerAdapter sessionHandler =
                new StompSessionHandlerAdapter() {
                    @Override
                    public void afterConnected(StompSession session,
                                               StompHeaders connectedHeaders) {
                        System.out.println("Connected to WebSocket server");
                        stompSession = session;
                        session.subscribe(
                                "/topic/message/send", new FrameHandler());
                    }
                };
        stompClient.connect("ws://localhost:8080/ws", sessionHandler);
    }

    private class FrameHandler implements StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return Message.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            Platform.runLater(controller::getMessages);
        }
    }
}
