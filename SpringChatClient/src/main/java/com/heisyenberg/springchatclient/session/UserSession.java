package com.heisyenberg.springchatclient.session;

import com.heisyenberg.springchatclient.models.ChatRoom;
import com.heisyenberg.springchatclient.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private User user;
    private ChatRoom chatRoom;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
}
