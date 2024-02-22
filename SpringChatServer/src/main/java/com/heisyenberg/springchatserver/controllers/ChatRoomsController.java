package com.heisyenberg.springchatserver.controllers;

import com.heisyenberg.springchatserver.models.ChatRoom;
import com.heisyenberg.springchatserver.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatRoomsController {
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomsController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/{}/getRooms")
    public List<ChatRoom> getRooms() {
        return chatRoomService.getRooms();
    }
}
