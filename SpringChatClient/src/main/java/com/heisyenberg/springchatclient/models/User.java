package com.heisyenberg.springchatclient.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"username", "password"})
@ToString(exclude = {"password"})
public class User {
    private Long id;
    private String username;
    private String password;
}
