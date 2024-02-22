package com.heisyenberg.springchatclient.services;

import com.heisyenberg.springchatclient.models.ChatRoom;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ChatRoomsService {
    @GET("/{user}/getRooms")
    Call<List<ChatRoom>> getRooms();
}
