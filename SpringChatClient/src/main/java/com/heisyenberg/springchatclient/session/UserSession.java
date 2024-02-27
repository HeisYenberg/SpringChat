package com.heisyenberg.springchatclient.session;

import com.heisyenberg.springchatclient.models.ChatRoom;
import com.heisyenberg.springchatclient.models.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private User user;
    private ChatRoom chatRoom;

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
}
