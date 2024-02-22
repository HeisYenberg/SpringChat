package com.heisyenberg.springchatclient.callbacks;

import com.heisyenberg.springchatclient.listcells.MessagesListCell;
import com.heisyenberg.springchatclient.models.Message;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MessagesCallback implements Callback<List<Message>> {
    private final ListView<Message> messageList;

    public MessagesCallback(ListView<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void onResponse(Call<List<Message>> call,
                           Response<List<Message>> response) {
        if (response.isSuccessful()) {
            List<Message> messages = response.body();
            Platform.runLater(() -> {
                messageList.getItems().setAll(messages);
                messageList.setCellFactory(param -> new MessagesListCell());
            });
        }
    }

    @Override
    public void onFailure(Call<List<Message>> call, Throwable throwable) {
    }
}
