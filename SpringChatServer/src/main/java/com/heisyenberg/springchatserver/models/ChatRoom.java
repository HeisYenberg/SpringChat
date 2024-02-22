package com.heisyenberg.springchatserver.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "chat_rooms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"name", "owner"})
@ToString
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
