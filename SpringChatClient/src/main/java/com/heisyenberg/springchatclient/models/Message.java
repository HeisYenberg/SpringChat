package com.heisyenberg.springchatclient.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"sender", "room", "text"})
@ToString()
public class Message {
    private Long id;
    private User sender;
    private ChatRoom room;
    private String text;
}
