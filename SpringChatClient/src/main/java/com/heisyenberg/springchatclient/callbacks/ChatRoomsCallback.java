package com.heisyenberg.springchatclient.callbacks;

import com.heisyenberg.springchatclient.listcells.ChatRoomsListCell;
import com.heisyenberg.springchatclient.models.ChatRoom;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ChatRoomsCallback implements Callback<List<ChatRoom>> {
    private final ListView<ChatRoom> roomsList;

    public ChatRoomsCallback(ListView<ChatRoom> roomsList) {
        this.roomsList = roomsList;
    }

    @Override
    public void onResponse(Call<List<ChatRoom>> call,
                           Response<List<ChatRoom>> response) {
        if (response.isSuccessful()) {
            List<ChatRoom> chatRooms = response.body();
            Platform.runLater(() -> {
                roomsList.getItems().setAll(chatRooms);
                roomsList.setCellFactory(param -> new ChatRoomsListCell());
            });
        }
    }

    @Override
    public void onFailure(Call<List<ChatRoom>> call, Throwable throwable) {
    }
}
