package com.heisyenberg.springchatclient.controllers;

import com.heisyenberg.springchatclient.ChatApplication;
import com.heisyenberg.springchatclient.callbacks.MessagesCallback;
import com.heisyenberg.springchatclient.models.ChatRoom;
import com.heisyenberg.springchatclient.models.Message;
import com.heisyenberg.springchatclient.models.User;
import com.heisyenberg.springchatclient.services.MessagesService;
import com.heisyenberg.springchatclient.services.RetrofitService;
import com.heisyenberg.springchatclient.session.UserSession;
import com.heisyenberg.springchatclient.websocketclients.MessagesWebSocketClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MessagesController implements Initializable {
    @FXML
    private ListView<Message> messagesList;
    @FXML
    private TextField messageText;
    @FXML
    private Label roomName;

    private MessagesWebSocketClient webSocketClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getMessages();
        roomName.setText(UserSession.getInstance().getChatRoom().getName());
        webSocketClient = new MessagesWebSocketClient(this);
        webSocketClient.connect();
    }

    private MessagesService getService() {
        RetrofitService retrofitService = new RetrofitService();
        return retrofitService.getRetrofit().create(MessagesService.class);
    }

    public void getMessages() {
        Long roomId = UserSession.getInstance().getChatRoom().getId();
        Call<List<Message>> messages = getService().getMessages(roomId);
        messages.enqueue(new MessagesCallback(messagesList));
    }

    @FXML
    public void onBackButton() {
        Stage stage = (Stage) messagesList.getScene().getWindow();
        try {
            ChatApplication.loadScene(stage, "chatrooms-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        webSocketClient.disconnect();
    }

    @FXML
    public void onSendButton() {
        String text = messageText.getText();
        if (text.isEmpty() || webSocketClient == null) {
            return;
        }
        if (text.length() >= 4096) {
            ChatApplication.showError("Message length is out of bounds");
            return;
        }
        User sender = UserSession.getInstance().getUser();
        ChatRoom room = UserSession.getInstance().getChatRoom();
        Message message = new Message(null, sender, room, text);
        webSocketClient.send("/app/sendMessage", message);
        messageText.clear();
    }
}
