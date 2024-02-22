package com.heisyenberg.springchatserver.services;

import com.heisyenberg.springchatserver.models.Message;
import com.heisyenberg.springchatserver.repositories.ChatRoomsRepository;
import com.heisyenberg.springchatserver.repositories.MessagesRepository;
import com.heisyenberg.springchatserver.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;
    private final ChatRoomsRepository chatRoomsRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository,
                          UsersRepository usersRepository,
                          ChatRoomsRepository chatRoomsRepository) {
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
        this.chatRoomsRepository = chatRoomsRepository;
    }

    public Message sendMessage(Message message) {
        return messagesRepository.save(message);
    }

    public List<Message> getMessagesByRoomId(Long id) {
        return messagesRepository.findMessagesByRoomId(id);
    }
}
