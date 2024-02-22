package com.heisyenberg.springchatserver.repositories;

import com.heisyenberg.springchatserver.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByRoomId(Long id);
}
