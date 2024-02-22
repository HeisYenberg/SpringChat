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
    private final UsersRepository usersRepository;
    private final ChatRoomsRepository chatRoomsRepository;

    @Autowired
    public ChatRoomService(SimpMessagingTemplate template,
                           UsersRepository usersRepository,
                           ChatRoomsRepository repository) {
        this.template = template;
        this.usersRepository = usersRepository;
        this.chatRoomsRepository = repository;
    }

    public Optional<ChatRoom> getRoomById(Long id) {
        return chatRoomsRepository.findById(id);
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
