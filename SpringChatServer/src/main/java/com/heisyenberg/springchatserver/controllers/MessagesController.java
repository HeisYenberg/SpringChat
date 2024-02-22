package com.heisyenberg.springchatserver.controllers;

import com.heisyenberg.springchatserver.models.Message;
import com.heisyenberg.springchatserver.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{}/{chatroom}/getMessages")
    public List<Message> getMessages(@PathVariable("chatroom") Long chatroom) {
        return messageService.getMessagesByRoomId(chatroom);
    }
}
