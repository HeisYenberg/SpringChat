package com.heisyenberg.springchatserver.controllers;

import com.heisyenberg.springchatserver.models.ChatRoom;
import com.heisyenberg.springchatserver.models.Message;
import com.heisyenberg.springchatserver.services.ChatRoomService;
import com.heisyenberg.springchatserver.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final ChatRoomService roomService;
    private final MessageService messageService;

    @Autowired
    public WebSocketController(ChatRoomService roomService,
                               MessageService messageService) {
        this.roomService = roomService;
        this.messageService = messageService;
    }

    @MessageMapping("/createRoom")
    @SendTo("/topic/room/create")
    public ChatRoom roomCreated(@Payload ChatRoom chatRoom) {
        roomService.createRoom(chatRoom);
        return chatRoom;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/message/send")
    public Message sendMessage(@Payload Message message) {
        return messageService.sendMessage(message);
    }
}
