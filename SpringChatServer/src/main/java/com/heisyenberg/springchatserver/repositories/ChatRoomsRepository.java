package com.heisyenberg.springchatserver.repositories;

import com.heisyenberg.springchatserver.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomsRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsChatRoomByName(String name);
}