package com.heisyenberg.springchatserver.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"sender", "room", "text"})
@ToString()
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom room;
    @Column(nullable = false)
    private String text;
}
