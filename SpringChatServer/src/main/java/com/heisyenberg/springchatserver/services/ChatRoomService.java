package com.heisyenberg.springchatserver.services;

import com.heisyenberg.springchatserver.models.ChatRoom;
import com.heisyenberg.springchatserver.repositories.ChatRoomsRepository;
import com.heisyenberg.springchatserver.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {
    private final SimpMessagingTemplate template;
    private final ChatRoomsRepository chatRoomsRepository;

    @Autowired
    public ChatRoomService(SimpMessagingTemplate template,
                           ChatRoomsRepository repository) {
        this.template = template;
        this.chatRoomsRepository = repository;
    }

    public void createRoom(ChatRoom room) {
        if (!chatRoomsRepository.existsChatRoomByName(room.getName())) {
            ChatRoom chatRoom = chatRoomsRepository.save(room);
            template.convertAndSend("/topic/rooms", chatRoom);
        }
    }

    public List<ChatRoom> getRooms() {
        return chatRoomsRepository.findAll();
    }
}
