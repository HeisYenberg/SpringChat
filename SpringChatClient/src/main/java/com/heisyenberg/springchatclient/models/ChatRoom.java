package com.heisyenberg.springchatclient.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"name", "owner"})
@ToString
public class ChatRoom {
    private Long id;
    private String name;
    private User owner;
}
